# LeetCode
This repository contains the solutions and explanations to the algorithm problems on LeetCode.  
All are written in Java and implemented by myself.   
Only cover Medium and hard problems and some interesting easy ones which need some documentation.

# Markdown Instruction:  
-这里写markdown有一个网友教程参考：[MarkDown入门参考](http://xianbai.me/learn-md/index.html)  
-这里是markdown的一个英文网络教程：[MarkDown tutorial](https://www.markdowntutorial.com/)  
-这是MS-DOS Commands总结整理：[MS-DOS Commands](https://www.c3scripts.com/tutorials/msdos/commands.html)  
下面是一些笔记摘要： 

## Character Pattern:  
-Italics: surround words with an underscore (_)  _this_ word would become italic  
-Bold: surround words with two asterisks ( ** ) This will **really** get your point across  
-bold and italic: **_on the outside_**  

## Headers: 
preface the phrase with a hash mark (#), place the same number of hash marks as the size of the header you want  
eg: # Header One; ### Header Three  
Remember: You can't really make a header bold, but you can italicize certain words.  

## Links: 
-The first link style is called an **inline link**. To create an inline link, you wrap the link text in brackets ( [ ] ), and then you wrap the link in parenthesis ( ( ) ). For example, to create a hyperlink to www.github.com, with a link text that says, Visit GitHub!, you'd write this in Markdown: [Visit GitHub!](www.github.com).  
**注释：这里[]方括号里面是显示出来的可以点击的link, ()圆括号是点击后转至的网络地址，在阅读的时候看不到，只是跳转的地址。**  
-The other link type is called a reference link. As the name implies, the link is actually a reference to another place in the document.  Here's [a link to something else][another place].  
Here's [yet another link][another-link].  
And now back to [the first link][another place].  

     [another place]: www.github.com  
     [another-link]: www.google.com  
     
## Images:
images are prefaced with an exclamation point ( ! )  
* inline image link  
enter an exclamation point ( ! )
wrap the alt text in brackets ( [ ] )
then wrap the link in parenthesis ( ( ) )  
eg: [A pretty tiger](https://upload.wikimedia.org/wikipedia/commons/5/56/Tiger.50.jpg)  
* reference image  
precede the Markdown with an exclamation point 
then provide two brackets for the alt text
then two more for the image tag, like this: ![The founding father][Father]
At the bottom of your Markdown page, you'll define an image for the tag, like this: [Father]: http://octodex.github.com/images/founding-father.jpg.  
eg:  
**注释：这里就是定义后面的中括号中的链接，前面中括号中是对后面中括号链接的引用，这样多次引用，一旦改变链接，只需要改变一次**  
[Black cat][Black]  

[Orange cat][Orange]  

[Black]: https://upload.wikimedia.org/wikipedia/commons/a/a3/81_INF_DIV_SSI.jpg  

[Orange]: https://upload.wikimedia.org/wikipedia/commons/a/a3/81_INF_DIV_SSI.jpg  

## Blockquotes：
To create a block quote, all you have to do is preface a line with the "greater than" caret (>)  

## Lists:
To create an unordered list, you'll want to preface each item in the list with an asterisk ( * )    
**注意，星号后面要有空格**  
* Flour
* Cheese
* Tomatoes

## Paragraphs:
想实现新的段落，只回车一次是不行的，显示出来会一直连着，所以要中间空一行（也就是敲两次回车），**hard break**  
另一种实现方法就是： **soft break** 敲两次**space空格** on the keyboard.  


