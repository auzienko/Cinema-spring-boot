<#import "/spring.ftl" as spring />
<#import "ui.ftl" as ui/>
<#assign titletext><@spring.message 'signin.title'/></#assign>
<@ui.headerWithjQuery title="👤 ${titletext}"/>
<form method="post" action="/signIn">
    <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden" />
    <div class="container">
        <table>
            <tr>
                <td><input name="email" placeholder="<@spring.message 'signin.email'/>" type="text" required/></td>
            </tr>
            <tr>
                <td><input name="password" placeholder="<@spring.message 'signin.password'/>" type="password" required/></td>
            </tr>
            <tr>
                <td>
                    <button type="Submit" class="btn"><@spring.message 'signin.submitButton'/></button>
                </td>
            </tr>
        </table>
    </div>
</form>
<#if error?has_content>
    <h1 style="text-align: center"><b>${error}</b></h1>
</#if>
<@ui.langchoiser/>
<@ui.tail/>