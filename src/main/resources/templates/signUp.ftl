<#import "/spring.ftl" as spring />
<#import "ui.ftl" as ui/>
<#assign titletext><@spring.message 'signup.title'/></#assign>
<@ui.headerWithjQuery title="👤 ${titletext}"/>
<form method="post" action="<@spring.url '/signUp'/>">
    <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden"/>
    <div class="container">
        <table>
            <tr>
                <td>
                    <@spring.bind 'userForm.username'/>
                    <#if spring.status.errorMessages?has_content>
                        <span class="error">${spring.status.errorMessages?first}</span>
                    </#if>
                    <input class="full-size" name="${spring.status.expression}" placeholder="<@spring.message 'signup.username'/>"
                           value="${spring.stringStatusValue}" type="text"/>
                </td>
            </tr>
            <tr>
                <td>
                    <@spring.bind 'userForm.email'/>
                    <#if spring.status.errorMessages?has_content>
                        <span class="error">${spring.status.errorMessages?first}</span>
                    </#if>
                    <input class="full-size" name="${spring.status.expression}" placeholder="<@spring.message 'signup.email'/>"
                           value="${spring.stringStatusValue}" type="text" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <@spring.bind 'userForm.password'/>
                    <#if spring.status.errorMessages?has_content>
                        <span class="error">${spring.status.errorMessages?first}</span>
                    </#if>
                    <input class="full-size" name="${spring.status.expression}" placeholder="<@spring.message 'signup.password'/>"
                           value="${spring.stringStatusValue}" type="password" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="Submit" class="btn"><@spring.message 'signup.submitButton'/></button>
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