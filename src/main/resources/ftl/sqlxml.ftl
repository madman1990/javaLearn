<#macro mapperEl value>${r"#{"}${value}}</#macro>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.imobpay.base.dao.${clsName}Dao">

	<#--  生成查询参数  -->
	
	<sql id="paramSql">
		<!--  此处一定要增加一个查询 -->
		<#list TABLINFO as x >
		<if test="${x.columnClsName} !=null and ${x.columnClsName} !=''">
			AND ${x.columnName} =  <@mapperEl  x.columnClsName  />
		</if>
		</#list>
	</sql>
	
	<#--  生成请求参数  -->
	<parameterMap type="com.imobpay.base.entity.${clsName}" id="paramId${clsName}">
		<parameter property="pageflag"  javaType="Boolean" jdbcType="VARCHAR" mode="IN" />
		<parameter property="curPage"   javaType="Integer" jdbcType="NUMERIC" mode="IN" />
		<parameter property="pageSize"  javaType="Integer" jdbcType="NUMERIC" mode="IN" />
		<#list TABLINFO as x >
		<parameter property="${x.columnClsName}" javaType="${x.columnClsType}" jdbcType="${x.columnType}" mode="IN"   />
		</#list>
	</parameterMap>

	<#--  生成返回参数  -->
	<resultMap type="com.imobpay.base.entity.${clsName}" id="resultTb${clsName}">
	<#list TABLINFO as x >
		<result column="${x.columnName}" property="${x.columnClsName}" javaType="${x.columnClsType}" jdbcType="${x.columnType}" />
	</#list>
	</resultMap>

	<#--  查询单个对像  -->
	<select id="selectById" parameterMap="paramId${clsName}" resultMap="resultTb${clsName}">
		SELECT * FROM ${tableName} WHERE  ROWNUM = 1  
		<include refid="paramSql"></include>
	</select>
	
	<#--  分页查询  -->
	<select id="list" parameterMap="paramId${clsName}" resultMap="resultTb${clsName}">
		<if test="pageflag==true">
			SELECT * FROM (
				SELECT A.*, ROWNUM RN FROM
				(
					SELECT * FROM ${tableName} WHERE 1=1
					<include refid="paramSql"></include>
				) A WHERE ROWNUM &lt;= <@mapperEl  'curPage'  />*<@mapperEl  'pageSize'  />
				) WHERE RN  &gt;
			(<@mapperEl  'curPage'  />-1)*<@mapperEl  'pageSize'  />
		</if>
		<if test="pageBlag==false">
			SELECT * FROM ${tableName}
			WHERE 1=1
			<include refid="paramSql"></include>
		</if>
	</select>
	
	<#--  分页个数查询  -->
	<select id="listCount" parameterMap="paramId${clsName}" resultType="Integer">
				SELECT count(1) as count FROM ${tableName}
				WHERE 1=1 
				<include refid="paramSql"></include>
	</select>
	
	<!-- 添加 -->
	<insert id="insert" parameterMap="paramId${clsName}" timeout="1000"  >
		INSERT INTO 
		${tableName}(
		<#list TABLINFO as x >
		<#if x.curSize == MAXSIZE>
		${x.columnName}
		<#else>${x.columnName},</#if>
		</#list>)
		VALUES(<#list TABLINFO as x ><#if x.curSize == MAXSIZE>
		<@mapperEl  x.columnClsName /><#else>
		<@mapperEl  x.columnClsName  />,
		</#if></#list>)
	</insert>
	
	<!-- 修改 -->
	
	<update id="update"  timeout="1000" >
		UPDATE ${tableName} 
		<trim prefix="SET" suffixOverrides=",">
		<#list TABLINFO as x >
		<if test="${x.columnClsName} !=null and ${x.columnClsName} !=''">
			${x.columnName} = <@mapperEl  x.columnClsName  /> ,
		</if>
		</#list>
		</trim>
		WHERE 1=1
		<include refid="paramSql"></include>
	</update>
	
</mapper>