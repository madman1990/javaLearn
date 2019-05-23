package com.imobpay.base.entity;

/**
 * 
 * <pre>
 * ClassName: ${clsName} <br/> 
 * Reason: ${clsName}相关内容. <br/> 
 * date: 2017年10月31日 下午4:20:38 <br/> 
 * 
 * @author Lance.Wu . <br/> 
 * @version   . <br/> 
 * @since JDK 1.6 PayCreditServer 1.0 . <br/> 
 * </pre>
 */
public class ${clsName} extends BaseEntity {

	/** 序号 */
    private static final long serialVersionUID = 1L;

	<#list TABLINFO as x>
   	/** 属性描述：${x.columnDesc} */	
    private ${x.columnClsType}            ${x.columnClsName};
   	</#list>
   	
  	<#list TABLINFO as x>
    /**
     * 描述：获取属性值-${x.columnDesc}.<br/>
     * 创建人：Wangpengliang <br/>
     * 返回类型：@return ${x.columnClsType} .<br/>
     */
    public ${x.columnClsType} get${x.columnClsName?cap_first}() {
        return ${x.columnClsName};
    }
 

    /**
     * 创建人：Wangpengliang <br/>
     * 创建时间：2016年6月3日 下午7:27:50 <br/>
     * 参数: @param ${x.columnClsName} :${x.columnDesc} 设置值. <br/>
     */
    public void set${x.columnClsName?cap_first}(${x.columnClsType} ${x.columnClsName}) {
        this.${x.columnClsName} = ${x.columnClsName};
    }
   
    </#list>
   	

}
