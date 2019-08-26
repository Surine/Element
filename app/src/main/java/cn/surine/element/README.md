### Structure
 - base: the utils,base class,common implement
 - core: the operate of desktop widget core, app implement theory
 - implements: widget implements
 - lib_js_runner: js support
 - lib_database: database of the app,but not the real sqlite, you can understand it as the source of the variety data 
 - lib_event: click event source
 - lib_widget: widget view for widget
 - ui: stand ui
 
 
 
### 动态布局解决方案：
    Json解析：实体类参考Product.java
     
    {
        "type":"Shape",
        "action":"NULL",
        "properties":[
          {"propertyName":"type","propertyType":"int","propertyValue":"CIRCLE"},
          {"propertyName":"radius","propertyType":"int","propertyValue":"50"},
          {"propertyName":"backgroundColor","propertyType":"String","propertyValue":"#fff"}
        ]
      },