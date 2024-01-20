### base-code-generator-pro

##### meta.json的作用
![img_5.png](img_5.png)

- 使用meta.josn来生成元信息->可以使用配置文件来配置要生成项目的信息
  - meta.json中,有fileConfig,其中的files为需要生成的项目的文件信息数组
    - 有fileConfig描述项目的信息,files描述单个文件的信息
  - meta.json中,有modelConfig,其中的models为填充需要生成项目的配置类信息
    - 注意models,使用gsonformatplus生成类时,defaultValue有两种类型,生成时只生成第一种类型
- 解决使用生成代码生成的代码的乱码问题
![img_3.png](img_3.png)
- 自动执行maven命令打包
- 自动生成win执行脚本
- 在生成项目中生成.ignore
- 在生成项目中生成README



### bug
在meta.json中,modelConfig的models的defaultValue有两种类型:String和boolea
![img_1.png](img_1.png)

读取meta.json中的元信息,author为String,但是生成的结果默认值为Boolean
![img.png](img.png)

原因:使用GsonFormatPlus插件生成的可能只识别第一个defaultValue的类型,然后创建meta类时,将defaultValue的类型赋值为boolean
解决方法:将meta类中的defaultValue类型扩大,修改为Object
![img_2.png](img_2.png)



