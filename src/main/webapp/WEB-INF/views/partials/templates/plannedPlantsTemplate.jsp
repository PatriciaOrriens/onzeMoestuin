<!-- Handlebars template for planned plants -->
<script id="plannedPlantsTemplate" type="text/x-handlebars-template">
    <br/>
    <h5>Deze planten staan klaar om te planten in je moestuin</h5>
    <div id="accordion" class="newPlantContainer">
        {{#each this}}
        <div class="card">
            <div class="card-header">
                <img src="/plant/{{plantId}}/image">
                <a class="card-link" data-toggle="collapse" href="#collapse-{{plantId}}">
                    {{plantInformation.plantName}}
                </a>
            </div>
            <div id="collapse-{{plantId}}" class="collapse" data-parent="#accordion">
                <div class="card-body">
                    <ul>
                        <li>{{plantInformation.plantName}} kan het best worden ingezaaid tussen <strong>{{plantInformation.sowingStart}}</strong> en <strong>{{plantInformation.sowingEnd}}</strong></li>
                        <li>Hanteer een <strong>plantafstand</strong> van ongeveer <strong>{{plantInformation.plantingDistance}}</strong> cm</li>
                        <li>Houdt van een plekje in de <strong>{{plantInformation.lighting}}</strong></li>
                        <li>Als alles goed gaat kun je {{plantInformation.plantName}} oogsten tussen <strong>{{plantInformation.harvestingStart}}</strong> en <strong>{{plantInformation.harvestingEnd}}</strong></li>
                    </ul>

                    <input type="button" value="Nu inzaaien" class="btn btn-success plantStartBtn" data-newPlantId="{{plantId}}" data-plantName="{{plantInformation.plantName}}"/>
                </div>
            </div>
        </div>
        {{/each}}
    </div>
</script>