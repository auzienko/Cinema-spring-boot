<#import "/spring.ftl" as spring />
<#import "../../ui.ftl" as ui/>
<#assign titletext><@spring.message 'hals.title'/></#assign>
<@ui.headerWithjQuery title="🍿  ${titletext}"/>
<#if error?has_content>
    <h1 style="text-align: center"><b>${error}</b></h1>
</#if>
<form method="post" action="halls">
    <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden"/>
    <div class="container">
        <table class="minimalistBlack" style="background-color: thistle">
            <tr>
                <td>
                    <label for="serialNumber">Enter hall's serial number:</label>
                    <input class="inp" name="serialNumber" type="number" value="0" min="0" max="42" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="seats">Enter count of seats:</label>
                    <input class="inp" name="seats" type="number" value="0" min="0" max="420" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="Submit" class="btn">Add hall</button>
                </td>
            </tr>
        </table>
    </div>
</form>

<#if movieHallList?has_content>
    <div class="container">
        <table class="minimalistBlack">
            <thead>
            <th>Serial number</th>
            <th>Seats count</th>
            </thead>
            <#list movieHallList as row>
                <tr>
                    <td>${row.serialNumber}</td>
                    <td>${row.seats}</td>
                </tr>
            </#list>
        </table>
    </div>
</#if>


<@ui.tail/>