# diceware

A kata I did to use the [diceware wordlist](http://world.std.com/~reinhold/diceware.html) to generate a password. You really shouldn't use this to generate passwords for yourself though because if your machine is compromised, any passwords you generate with it will also be compromised. I just did it for fun.

## Usage

To output n random diceware wordlist words do this

    $ java -jar diceware-0.1.0-standalone.jar [n]
    
If you don't supply an n, it will default to 5.

## Docker

You can also run this project using Docker:

### Building the Docker image

```bash
docker build -t diceware .
```

The Dockerfile uses a multi-stage build approach to create a minimal image (~153MB) that contains only what's needed to run the application.

### Running with Docker

To generate a password with the default number of words (5):

```bash
docker run --rm diceware
```

To specify the number of words (e.g., 7):

```bash
docker run --rm diceware 7
```

The `--rm` flag automatically removes the container when it exits, which is recommended for this type of single-use container.

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
