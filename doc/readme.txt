
--srcĿ¼�ṹ˵����

activity �����ڴ�����л��صĴ��룬 db �����ڴ���������ݿ���صĴ��룬
model �����ڴ������ģ����صĴ��룬 receiver �����ڴ�����й㲥��������صĴ��룬
service �����ڴ�����з�����صĴ��룬 util �����ڴ�����й�����صĴ���


--�������ݿ��

������׼���������ű� Province�� City�� County���ֱ�
���ڴ��ʡ���С��صĸ���������Ϣ�����ű�Ľ������ֱ����¡�

Province��
create table Province (
id integer primary key autoincrement,
province_name text,
province_code text)
���� id �������������� province_name ��ʾʡ���� province_code ��ʾʡ�����š�

City��
create table City (
id integer primary key autoincrement,
city_name text,
city_code text,
province_id integer)
���� id �������������� city_name ��ʾ�������� city_code ��ʾ�м����ţ� province_id ��
City ����� Province ��������

County��
create table County (
id integer primary key autoincrement,
county_name text,
county_code text,
city_id integer)
���� id �������������� county_name ��ʾ������ county_code ��ʾ�ؼ����ţ� city_id ��
County ����� City ��������
