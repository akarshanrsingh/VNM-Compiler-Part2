1,$s/^ *//
1,$s/ *::=/ :\n{}\n{/
1,$s/^{ */{\t/
1,$s/[a-z][a-z0-9_]*/&()/g
1,$s/[A-Z][A-Z0-9_]*/<&>/g
1,$s/.*:/void &/
1,$s/\r//g
1,$s/^$/}\n/
1,$s/void start()/& throws ParseException/
1,$s/ |/\n|/g
1a|\t< EOF > {throw new ParseException("End of File.");}
