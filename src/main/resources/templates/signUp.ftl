<#import "/spring.ftl" as spring />
<#import "ui.ftl" as ui/>
<#assign titletext><@spring.message 'signup.title'/></#assign>
<@ui.headerWithjQuery title="👤 ${titletext}"/>
<form method="post" action="/signUp">
    <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden" />
    <div class="container">
        <table>
            <tr>
                <td><input name="username" placeholder="<@spring.message 'signup.username'/>" type="text" required/></td>
            </tr>
            <tr>
                <td><input name="email" placeholder="<@spring.message 'signup.email'/>" type="text" required/></td>
            </tr>
            <tr>
                <td><input name="password" placeholder="<@spring.message 'signup.password'/>" type="password" required/></td>
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
<label for="locales"><@spring.message 'lang.change'/>
    <select id="locales">
        <#if .locale == "ru">
            <option value="ru"><@spring.message 'lang.ru' /></option>
            <option value="en"><@spring.message 'lang.eng' /></option>
        <#else>
            <option value="en"><@spring.message 'lang.eng' /></option>
            <option value="ru"><@spring.message 'lang.ru' /></option>
        </#if>
    </select>
</label>
<script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('?lang=' + selectedOption);
            }
        });
    });
</script>
<@ui.tail/>