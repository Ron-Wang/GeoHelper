# GeoHelper（地质助手）v1.0

GeoHelper是基于手机移动终端开发的，集岩体结构面野外量测、数据存储导出及可视化统计分析等于一体的移动信息平台。GeoHelper的开发应用，大大提高了岩体产状的测量效率，为科研人员、野外地质工作者及学生等提供了便捷的野外地质测量工具。  

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/测量.jpg width=400dp>
</div>

## 软件特点（Software Features）

节理面的测量统计是进行岩体受力变形分析的基础，因此野外进行节理面产状的测量、对采集数据进行统计绘图以及后期的分析计算就显得尤为重要。传统的产状测量主要依靠机械罗盘，野外测量的效率较低（测量一个产状大约需要一分钟），工作强度大。  
GeoHelper首次将手机应用于岩体结构测量与可视化统计分析，极大地提高了结构面产状的测量效率，同时实现了在野外实时的统计分析，是地质领域一个非常实用的、便捷的移动信息平台。

## 运行环境（Operating Environment）

* 硬件环境：具有**加速度传感器**和**磁阻传感器**的各类Android手机或者平板电脑
* 软件环境：Android4.2及以上版本

## 运行界面（Operating Interface）

* **主界面（Main Interface）**  

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/000主界面.png width=300dp>
</div>

* **产状测量界面（Rock Joints Measuring Interface）**  

测量前的准备工作类似指南针，应该远离干扰源，并将手机绕“8”字晃动重新校准手机指南针。  
测量时，需要将手机紧贴要测量的结构面，这里对手机摆放方向没有要求。待界面上的测量结果稳定后即可读数或记录，软件提供了数据存储（可标记优势结构面）、删除及导出等功能。  

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/001产状测量.png width=300dp>
</div>

<div align="center">
GeoHelper与机械罗盘的测量数据对比
</div>

<div align="center">
  
序号 | 罗盘倾向 | GeoHelper倾向 | 罗盘倾角 | GeoHelper倾角  
---- | ---- | ---- | ---- | ------  
1 | 358.0 | 1.0 | 20.0 | 21.0  
2 | 53.0 | 55.0 | 21.0 | 20.0  
3 | 121.0 | 122.0 | 18.0 | 19.0  
4 | 187.0 | 189.0 | 18.0 | 17.0  
5 | 249.0 | 252.0 | 27.0 | 27.0  
6 | 176.0 | 177.0 | 41.0 | 42.0  
7 | 181.0 | 180.0 | 46.0 | 46.0  
8 | 293.0 | 295.0 | 67.0 | 69.0  
9 | 286.0 | 286.0 | 55.0 | 53.0  
10 | 337.0 | 335.0 | 67.0 | 66.0  
11 | 210.0 | 208.0 | 59.0 | 58.0  
12 | 179.0 | 181.0 | 43.0 | 46.0  
13 | 343.0 | 339.0 | 48.0 | 51.0  
14 | 272.0 | 276.0 | 56.0 | 58.0  
15 | 286.0 | 284.0 | 67.0 | 65.0  

</div>

* **绘图界面（Drawing Interface）**  
*赤平投影图*  
输入各个节理面信息（倾向、倾角），最多支持三组节理面  

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/002绘图-01.png width=300dp>
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/002绘图-02.png width=300dp>
</div>

对于节理极点图、节理走向等玫瑰花图及节理等密度图，需要选择数据文件输入，读取的文件格式是txt，其中数据形式如下（编号 倾向 倾角 是否标记）：

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/txt.png width=600dp>
</div>

*节理极点图*  


<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/002绘图-03.png width=300dp>
</div>

*节理走向等玫瑰花图*  

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/002绘图-04.png width=300dp>
</div>

*节理等密度图*  

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/002绘图-05.png width=300dp>
</div>

* **系数计算界面（Coefficient Calculating Interface）**  

GeoHelper中楔形体稳定系数的计算，参照的是《边坡工程理论与实践最新发展》一书中的Hoek法。  

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/003系数计算-02.png width=300dp>
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/003系数计算-03.png width=300dp>
</div>


* **云平台界面（Cloud Platform Interface）**  

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/004云平台.png width=300dp>
</div>

* **地质资料界面（Geological Data Interface）**  

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/005地质资料-00.png width=300dp>
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/005地质资料-01.png width=300dp>
</div>

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/005地质资料-02.png width=300dp>
</div>

* **联系我们界面（Contacting Interface）**  

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/SmallImage/006联系我们-01.png width=300dp>
</div>

## 使用说明（Manual）

<div align="center">
  <img src=https://github.com/Ron-Wang/GeoHelper/blob/master/Image/result.gif width=300dp>
</div>

详见：GeoHelper(地质助手)1.0使用说明书.pdf</br>
https://github.com/Ron-Wang/GeoHelper/blob/master/GeoHelper(地质助手)1.0使用说明书.pdf
