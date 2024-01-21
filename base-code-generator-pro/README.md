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

- 提高代码的可移植性
  - 在meta.json中添加sourceRootPath字段
  - 然后在程序中将代码模板项目从sourceRootPath中复制到inputRootPath中
  - 再进行项目的处理
  - sourceRootPath 为模板项目的rootPath--需要带项目名
  - inputRootPath 暂时存放模板项目的路径，为生成最终项目的模板项目来源路径--需要带项目名
  - outputRootPath 为输出最终模板项目的路径--不用带项目名
  - sourceRootPath 末尾的项目名需要与inputRootPath末尾的项目名一致
- 增加项目的介绍
  - 做一个README.md的模板
- 简化代码，优化生成代码的空间
  - 删除生成代码的源代码，target，pom.xml文件，只留下jar包和执行的脚本文件
- 提高代码的健壮性
  - 校验meta.json中的属性是否符合要求
  - 自定义异常类
- 降低代码的圈复杂度
- 提高代码的可扩展性
  - 在项目中抽出常量值
  - 使用模板方法模式

### bug
在meta.json中,modelConfig的models的defaultValue有两种类型:String和boolea
![img_1.png](img_1.png)

读取meta.json中的元信息,author为String,但是生成的结果默认值为Boolean
![img.png](img.png)

原因:使用GsonFormatPlus插件生成的可能只识别第一个defaultValtiue的类型,然后创建meta类时,将defaultValue的类型赋值为boolean
解决方法:将meta类中的defaultValue类型扩大,修改为Object
![img_2.png](img_2.png)


