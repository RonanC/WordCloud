# WordCloud
###### Word Cloud program created from either a file or url. Written in Java.
**by Ronan Connolly**  

![oopAss cover](https://github.com/RonanC/WordCloud/blob/master/cover.png "oopAss cover")

Contents:
---------
1. About
2. Structure
3. Tools & Environment used
4. Installation
5. Use
6. Future Features
7. References
8. Team

1 - About
---
The aim of this project is to read in data from either a local file or url, parse out the words we want *(removing punctuation and html code/tags)*, sort it by how many times the word appears and then draw the words in a word cloud with different, colours and fonts (possibly incorporating a spiral/randomized greedy algorithm for word layout).

Basically what you see as the word cloud above.

Inspiration from [Jonathan Feinberg](http://mrfeinberg.com/), the creator of [Wordle](http://www.wordle.net/).


2 - Structure
---
###Packages
- ie.gmit.sw.io
- ie.gmit.sw.draw
- ie.gmit.sw.runner


3 - Tools & Environment used
---
- Eclipse Mars (Version: Mars.1 Release (4.5.1))
- Jsoup Jar (jsoup-1.8.3.jar core library)
- Mac OSX El Capitan (Version 10.11.2)


4 - Installation
---
###Run
In order to allow the jar to be exectuable do the following: (unix)
```sh
> sudo chmod +x WordCloud.jar
```

Next create a script to run the jar called *"WordCloud.sh"*
```sh
#!/bin/sh
java -jar WordCloud.jar
```

Finally run the script  
```sh
> ./WordCloud.sh
```

###Dependencies  
- Jsoup (http://jsoup.org/)

6 - Use
---
- Either enter a filename or a url
- A wordcloud will be draw in front of you
- You can take a screenshot if you wish to save (there are permission issues when writing locally via Java)


6 - Future Features
---
- Implement program (jar) as an applet in a website (java web project).


7 - References
---
- JSoup (http://jsoup.org/
- Oracle Java 2D Graphics Tutorial (https://docs.oracle.com/javase/tutorial/2d/)



8 - Team
---
This project was created for the OOP module in GMIT during semester one of fourth year Software Development for Dr. John Healy (lecturer).

<a href="https://github.com/RonanC"><img src="https://github.com/RonanC/DodgySpike/blob/master/PromoImages/Ronan.png" width="100px" height="100px" title="Ronan" alt="Ronan Image"/></a>
