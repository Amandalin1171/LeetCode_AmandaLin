# 快慢指针类问题
* while循环退出条件设计的思考：
为啥退出条件是runner != null和runner.next != null呢？  
因为进入循环的时候要求runner这个点本身不能使null，我们还要用到runner.next.next，所以runner.next必须是可以有next语句的必须可以指向下一个点， 所以runner.next不能使null，但是runner.next.next是null是可以的，这样下一次就退出循环了。

