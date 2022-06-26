<#import "/spring.ftl" as spring />
<#import "ui.ftl" as ui/>
<#assign titletext><@spring.message 'confirm.title'/></#assign>
<@ui.headerWithjQuery title="👤 ${titletext}"/>
<#if success?has_content>
    <p style="text-align: center"><b><@spring.message 'confirm.verification.success'/></b></p>
    <form action="/signIn" method="get">
        <div class="container">
            <button type="submit" class="btn">👤 <@spring.message 'confirm.singInButton'/></button>
        </div>
    </form>
<#else>
    <p style="text-align: center"><b><@spring.message 'confirm.empty.params'/></b></p>
</#if>
<@ui.langchoiser/>
<@ui.tail/>