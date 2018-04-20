package com.github.dapeng.router;


import com.github.dapeng.router.condition.*;
import com.github.dapeng.router.pattern.*;
import com.github.dapeng.router.token.*;

import java.util.ArrayList;
import java.util.List;

import static com.github.dapeng.router.RoutesLexer1.*;
import static com.github.dapeng.router.token.Token.STRING;

/**
 * routes :  (route eol)*
 * <p>
 * route  : left '=>' right
 * left  : 'otherwise'
 * | matcher (';' matcher)*
 * <p>
 * matcher : id 'match' patterns
 * patterns: pattern (',' pattern)*
 * <p>
 * pattern : '~' pattern
 * | string
 * | regexpString
 * | rangeString
 * | number
 * | ip
 * | kv
 * | mod
 * <p>
 * right : rightPattern (',' rightPattern)*
 * rightPattern : '~' rightPattern
 * | ip
 */

/**
 * 描述: 语法, 路由规则解析
 *
 * @author hz.lei
 * @date 2018年04月13日 下午9:34
 */
public class RoutesParser {

    private RoutesLexer1 lexer;

    public RoutesParser(RoutesLexer1 lexer) {
        this.lexer = lexer;
    }

    /**
     * 第一步： 多行路由规则，根据回车符 ' \n '  进行split  do while 解析
     */
    public List<Route> routes() {
        List<Route> routes = new ArrayList<>();
        Token token = lexer.peek();
        switch (token.id()) {
            case Token.EOL:
            case Token.OTHERWISE:
            case Token.ID:
                Route route = route();
                routes.add(route);
                while (lexer.peek() == Token_EOL) {
                    lexer.next(Token.EOL);
                    routes.add(route());
                }
                break;

            default:
                error("expect `otherwise` or `id match ...` but got " + token);
        }
        return routes;
    }

    /**
     * 解析一条路由规则，形如:
     * route  : left '=>' right
     * <p>
     * method match s'getFoo'  => ~ip'192.168.3.39'
     */
    public Route route() {
        Token token = lexer.peek();
        switch (token.id()) {
            case Token.OTHERWISE:
            case Token.ID:
                Condition left = left();
                lexer.next(Token.THEN);
                List<ThenIp> right = right();
                return new Route(left, right);
            default:
                error("expect `otherwise` or `id match ...` but got " + token);
        }
        return null;
    }

    /**
     * left  : 'otherwise' matcher (';' matcher)*
     * <p>
     * method match pattern1,pattern2
     */

    /**
     * method match s'getFoo',s'setFoo' ; version match s'1.0.0',s'1.0.1' => right
     * 分号 分隔 之间 是一个 Matcher
     * <p>
     * 一个 Matcher 有多个 pattern
     *
     * @return
     */
    public Condition left() {
        Matchers matchers = new Matchers();
        Token token = lexer.peek();
        switch (token.id()) {
            case Token.OTHERWISE:
                lexer.next();
                return new Otherwise();
            case Token.ID:
                Matcher matcher = matcher();
                matchers.macthers.add(matcher);
                while (lexer.peek() == Token_SEMI_COLON) {
                    lexer.next(Token.SEMI_COLON);
                    matchers.macthers.add(matcher());
                }
                return matchers;
            default:
                error("expect `otherwise` or `id match ...` but got " + token);
                return null;
        }
    }

    /*
     matcher : id 'match' patterns
     */

    /**
     * method match "getFoo","setFoo"
     *
     * @return
     */
    public Matcher matcher() {

        // method
        IdToken id = (IdToken) lexer.next();
        // match
        lexer.next(Token.MATCH);
        List<Pattern> patterns = patterns();

        return new Matcher(id.name, patterns);
    }

    /**
     * patterns: pattern (',' pattern)*
     * <p>
     * pattern : '~' pattern
     * | string
     * | regexpString
     * | rangeString
     * | number
     * | ip
     * | kv
     * | mod
     */

    /**
     * method match s'getFoo',s'setFoo';version match s'1.0.0',s'1.0.1' => right    (1)
     * <p>
     * method match s'getFoo',s'setFoo' => right                (2)
     *
     * @return
     */
    public List<Pattern> patterns() {
        List<Pattern> patterns = new ArrayList<>();

        Pattern p = pattern();
        patterns.add(p);
        while (lexer.peek() == Token_COMMA && lexer.peek() != Token_SEMI_COLON && lexer.peek() != Token_THEN) {
            lexer.next(Token.COMMA);
            patterns.add(pattern());
        }


        return patterns;
    }

    /**
     * s'getFoo'
     * <p>
     * s'setFoo'
     * <p>
     * s'getFoo*'
     *
     * @return
     */
    public Pattern pattern() {
        // s'getFoo'
        Token token = lexer.peek();
        switch (token.id()) {
            case Token.NOT:
                lexer.next(Token.NOT);
                Pattern it = pattern();
                return new NotPattern(it);
            case STRING:
                // getFoo
                StringToken st = (StringToken) lexer.next(Token.STRING);
                return new StringPattern(st.content);
            case Token.REGEXP:
                // get.*
                RegexpToken regexp = (RegexpToken) lexer.next(Token.REGEXP);
                return new RegexpPattern(regexp.regexp);
            case Token.RANGE:
                // getFoo
                RangeToken rt = (RangeToken) lexer.next(Token.RANGE);
                return new RangePattern(rt.from, rt.to);
            case Token.NUMBER:
                NumberToken nt = (NumberToken) lexer.next(Token.NUMBER);
                return new NumberPattern(nt.number);
            case Token.IP:
                IpToken ipToken = (IpToken) lexer.next(Token.IP);
                return new IpPattern(ipToken.ip, ipToken.mask);
            case Token.KV:
            case Token.MODE:
                ModeToken modeToken = (ModeToken) lexer.next(Token.MODE);
                return new ModePattern(modeToken.base, modeToken.from, modeToken.to);
            default:
                return null;
        }
    }

    /**
     * right : rightPattern (',' rightPattern)*
     * rightPattern : '~' rightPattern
     * | ip
     */
    public List<ThenIp> right() {
        List<ThenIp> thenIps = new ArrayList<>();

        Token token = lexer.peek();
        switch (token.id()) {
            case Token.NOT:
            case Token.IP:
                ThenIp it = rightPattern();
                thenIps.add(it);
                while (lexer.peek() == Token_COMMA) {
                    lexer.next(Token.COMMA);
                    ThenIp it2 = rightPattern();
                    thenIps.add(it2);
                }
                return thenIps;
            default:
                error("expect '~ip' or 'ip' but got:" + token);
                return null;
        }
    }

    /**
     * ？
     *
     * @return
     */
    public ThenIp rightPattern() {
        Token token = lexer.peek();
        switch (token.id()) {
            case Token.NOT: {
                lexer.next(Token.NOT);
                ThenIp it = rightPattern();
                return new ThenIp(!it.not, it.ip, it.mask);
            }
            case Token.IP: {
                IpToken ip = (IpToken) lexer.next(Token.IP);
                return new ThenIp(false, ip.ip, ip.mask);
            }
            default:
                error("expect '~ip' or 'ip' but got:" + token);
                return null;
        }
    }

    private void error(String s) {

    }


}
