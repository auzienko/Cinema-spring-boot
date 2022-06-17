<#import "ui.ftl" as ui/>
<@ui.header title="👤 Sign Up"/>
<form method="post" action="/signUp">
    <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden" />
    <div class="container">
        <table>
            <tr>
                <td><input name="username" placeholder="Username" type="text" required/></td>
            </tr>
            <tr>
                <td><input name="email" placeholder="Email" type="text" required/></td>
            </tr>
            <tr>
                <td><input name="password" placeholder="Password" type="password" required/></td>
            </tr>
            <tr>
                <td>
                    <button type="Submit" class="btn">Sign me Up</button>
                </td>
            </tr>
        </table>
    </div>
</form>
<#if error?has_content>
    <h1 style="text-align: center"><b>${error}</b></h1>
</#if>
<@ui.tail/>