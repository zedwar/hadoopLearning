mapper：把数据给变成kv值
mapreduce工作流程：
文本：先在客户端切片，然后获得信息提交上去
yarn和RM划分Maptask
然后把数据交给mapper
然后mapper处理完交给环形缓冲区
然后到shuffle阶段。
从mapper出来的数值对key做全排序完成分组
但是排序不能全局排序，只能部分排序，然后使用归并排序
写入到内存是用快排
启动reducer
因为之前是不同块，但是还是需要合并文件归并排序
然后输出
paitition分区：
分区表明数据应该去哪个reducertask
如何是一个数变为正数，使用&
shuffle过程
map方法到环形缓冲区，在缓冲区中完成分区和排序的工作
combiner
在map节点上执行排序
combiner大声在第一次归并排序