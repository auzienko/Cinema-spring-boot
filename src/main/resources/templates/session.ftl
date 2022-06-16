<#import "ui.ftl" as ui/>
<@ui.header title="Session info:"/>
<#if sessionInfo?has_content>
    <div class="container" style="text-align: center;">
        <h3>Hall: ${sessionInfo.movieHall.serialNumber} | Cost: $${sessionInfo.cost} </h3>
        <h3>Date:
            ${sessionInfo.dateTime.hour}:${sessionInfo.dateTime.minute}
            ${sessionInfo.dateTime.dayOfMonth}.${sessionInfo.dateTime.monthValue?string["00"]}.${sessionInfo.dateTime.year?replace(",","")}</h3>
        <h2>
            <b>${sessionInfo.movie.title}</b>
            ( ${sessionInfo.movie.yearOfRelease?replace(",","")} )
            ${sessionInfo.movie.ageRestrictions}+ </h2>
        <div><img src="/images/${sessionInfo.movie.poster.fileNameUUID}"></div>
        <div><p>${sessionInfo.movie.description}</p></div>
    </div>
</#if>
<@ui.tail/>
