# diceware

A kata I did to use the [diceware wordlist](http://world.std.com/~reinhold/diceware.html) to generate a password. You really shouldn't use this to generate passwords for yourself though because if your machine is compromised, any passwords you generate with it will also be compromised. I just did it for fun.

## Usage

To output n random diceware wordlist words do this

    $ java -jar diceware-0.1.0-standalone.jar [n]
    
If you don't supply an n, it will default to 5.

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
