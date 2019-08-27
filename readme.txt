xml
json:轻量级前后端数据交换的一种格式。

java<--> json

Animal
    name
    age
    sex


    Animal a = new Animal();

    a在前端如何显示
    a -> 格式化string -> json
    {}:代表对象
    []:代表列表
    {
        "name":"a1",
        "age":2,
        "sex":"m"
    }

    List<Animal>
    [
        {

        }
        {}
        {}
    ]

json  ->  java
{
    "name":"a1",
    "age":2,
    "sex":"m"
}

class Animal{
    String name;
    int age;
    String sex;
}

自动转化
Gson 谷歌
fast json 阿里巴巴
jackson