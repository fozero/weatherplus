
--src包结构

activity 包用于存放所有活动相关的代码， db 包用于存放所有数据库相关的代码，
model 包用于存放所有模型相关的代码， receiver 包用于存放所有广播接收器相关的代码，
service 包用于存放所有服务相关的代码， util 包用于存放所有工具相关的代码

--天气接口

有很多免费的天气预报接口，如新浪天气、雅虎天气  ，这里使用中国天气网提供的 API 接口

比如要想罗列出中国所有的省份，只需访问如下地址：
http://www.weather.com.cn/data/list3/city.xml
01|北京,02|上海,03|天津

想知道某个省内有哪些城市呢，其实也很简单，比如江苏的省级代号是 19，访问如下地址即可：
http://www.weather.com.cn/data/list3/city19.xml
1901| 南 京 ,1902| 无 锡 ,1903| 镇 江


想要知道苏州市下又有哪些县的时候，只需访问如下地址：
http://www.weather.com.cn/data/list3/city1904.xml
190401| 苏 州 ,190402| 常 熟 ,190403| 张 家 港

通过这种方式，我们就能把全国所有的省、市、县都罗列出来了。

查看到具体的天气信息，这就必须找到某个地区对应的天气代号。比如说昆山的县级代号是 190404，那么访问如下地址：
http://www.weather.com.cn/data/list3/city190404.xml
190404|101190404

访问查询天气接口，将相应的天气代号填入即可，接口地址如下：
http://www.weather.com.cn/data/cityinfo/101190404.html
{"weatherinfo":
{"city":"昆山","cityid":"101190404","temp1":"21℃","temp2":"9℃",
"weather":"多云转小雨","img1":"d1.gif","img2":"n7.gif","ptime":"11:00"}
}
其中 city 表示城市名， cityid 表示城市对应的天气代号， temp1 和 temp2 表示气温是几度
到几度， weather 表示今日天气信息的描述， img1 和 img2 表示今日天气对应的图片， ptime表示天气发布的时间



--基础功能

1. 可以罗列出全国所有的省、市、县。
2. 可以查看全国任意城市的天气信息。
3. 可以自由地切换城市，去查看其他城市的天气。
4. 提供手动更新以及后台自动更新天气的功能。

--待添加功能

1. 增加设置选项，让用户选择是否允许后台自动更新天气，以及设定更新的频率。
2. 优化软件界面，提供多套与天气对应的图片，让程序可以根据不同的天气自动切换
背景图。
3. 允许选择多个城市，可以同时观察多个城市的天气信息，不用来回切换。
4. 提供更加完整的天气信息，包括未来几天的天气情况、风力指数、生活建议等。

--建表语句   

Province：
create table Province (
id integer primary key autoincrement,
province_name text,
province_code text)
其中 id 是自增长主键， province_name 表示省名， province_code 表示省级代号。

City：
create table City (
id integer primary key autoincrement,
city_name text,
city_code text,
province_id integer)
其中 id 是自增长主键， city_name 表示城市名， city_code 表示市级代号， province_id 是
City 表关联 Province 表的外键。

County：
create table County (
id integer primary key autoincrement,
county_name text,
county_code text,
city_id integer)
其中 id 是自增长主键， county_name 表示县名， county_code 表示县级代号， city_id 是
County 表关联 City 表的外键。