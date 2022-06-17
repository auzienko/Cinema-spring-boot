<#import "ui.ftl" as ui/>
<@ui.header title="👤 Sign In"/>
<form method="post" action="/signIn">
    <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden" />
    <div class="container">
        <table>
            <tr>
                <td><input name="email" placeholder="Enter email" type="text" required/></td>
            </tr>
            <tr>
                <td><input name="password" placeholder="Enter password" type="password" required/></td>
            </tr>
            <tr>
                <td>
                    <button type="Submit" class="btn">Sign me In</button>
                </td>
            </tr>
        </table>
    </div>
</form>
<#if error?has_content>
    <h1 style="text-align: center"><b>${error}</b></h1>
</#if>
<@ui.tail/>