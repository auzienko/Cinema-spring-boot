<#import "../../ui.ftl" as ui/>
<@ui.header title="🌅 Sessions panel"/>
<#if error?has_content>
    <h1 style="text-align: center"><b>${error}</b></h1>
</#if>
<form method="post" action="sessions">
    <div class="container">
        <table>
            <tr>
                <td>
                    <label for="movie">Select a film:</label>
                    <select name="movie">
                        <#list movieList as movie>
                            <option value="${movie.id}">${movie.title}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="hall">Select a hall:</label>
                    <select name="hall">
                        <#list movieHallList as hall>
                            <option value="${hall.id}">${hall.serialNumber}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="dateTime">Enter session date and time</label>
                    <input name="dateTime" type="datetime-local" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="cost">Enter ticket cost:</label>
                    <input name="cost" type="number" value="100" min="0" max="1000" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="Submit" class="btn">Add session</button>
                </td>
            </tr>
        </table>
    </div>
</form>
<#if sessionList?has_content>
    <div class="container">
        <table class="minimalistBlack">
            <thead>
            <th>Date and time</th>
            <th>Film title</th>
            <th>Movie hall serial number</th>
            <th>Ticker cost</th>
            </thead>
            <#list sessionList as row>
                <tr>
                    <td>${row.dateTime}</td>
                    <td>${row.movie.title}</td>
                    <td>${row.movieHall.serialNumber}</td>
                    <td>${row.cost}</td>
                </tr>
            </#list>
        </table>
    </div>
</#if>
<@ui.tail/>