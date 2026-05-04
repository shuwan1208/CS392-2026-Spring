import MyLibrary.FnList.*;
import MyLibrary.LnStrm.*;

public class Final_01 {
    private static final class ParseResult {
        FnList<Character> word;
        LnStrm<Character> rest;

        ParseResult(FnList<Character> word, LnStrm<Character> rest) {
            this.word = word;
            this.rest = rest;
        }
    }

    static LnStrm<FnList<Character>> pg2701_word$strmize() {
        return wordStream(Final_00.pg2701_char$strmize());
    }

    private static LnStrm<FnList<Character>> wordStream(LnStrm<Character> cs) {
        return new LnStrm<FnList<Character>>(
            () -> {
                ParseResult res = parseNextWord(cs);
                if (res == null) {
                    return new LnStcn<FnList<Character>>();
                }
                return new LnStcn<FnList<Character>>(res.word, wordStream(res.rest));
            }
        );
    }

    private static ParseResult parseNextWord(LnStrm<Character> cs) {
        LnStcn<Character> cell;
        char ch;
        while (true) {
            cell = cs.eval0();
            if (cell.nilq()) {
                return null;
            }
            ch = cell.hd();
            cs = cell.tl();
            if (wordCharq(ch)) {
                break;
            }
        }
        FnList<Character> rev = FnListSUtil.nil();
        rev = FnListSUtil.cons(lowerCase(ch), rev);
        while (true) {
            cell = cs.eval0();
            if (cell.nilq()) {
                return new ParseResult(FnListSUtil.reverse(rev), new LnStrm<Character>());
            }
            ch = cell.hd();
            cs = cell.tl();
            if (!wordCharq(ch)) {
                return new ParseResult(FnListSUtil.reverse(rev), cs);
            }
            rev = FnListSUtil.cons(lowerCase(ch), rev);
        }
    }

    static boolean wordCharq(char ch) {
        return ('a' <= ch && ch <= 'z')
            || ('A' <= ch && ch <= 'Z')
            || ch == '\'';
    }

    static char lowerCase(char ch) {
        if ('A' <= ch && ch <= 'Z') {
            return (char)(ch - 'A' + 'a');
        }
        return ch;
    }

    static int wordCompare(FnList<Character> xs, FnList<Character> ys) {
        while (xs.consq() && ys.consq()) {
            int sgn = xs.hd() - ys.hd();
            if (sgn != 0) {
                return sgn;
            }
            xs = xs.tl();
            ys = ys.tl();
        }
        if (xs.nilq() && ys.nilq()) {
            return 0;
        }
        return xs.nilq() ? -1 : 1;
    }

    static String word2string(FnList<Character> xs) {
        StringBuilder sb = new StringBuilder();
        while (xs.consq()) {
            sb.append(xs.hd());
            xs = xs.tl();
        }
        return sb.toString();
    }

    static FnList<Character> string2word(String word) {
        FnList<Character> res = FnListSUtil.nil();
        for (int i = word.length() - 1; i >= 0; i -= 1) {
            res = FnListSUtil.cons(word.charAt(i), res);
        }
        return res;
    }

    public static void main(String[] args) {
        LnStrm<FnList<Character>> ws = pg2701_word$strmize();
        int i = 0;
        while (i < 20) {
            LnStcn<FnList<Character>> cell = ws.eval0();
            if (cell.nilq()) {
                break;
            }
            System.out.println(word2string(cell.hd()));
            ws = cell.tl();
            i += 1;
        }
    }
}
