<!-- Handlebars template for Plant modal -->
<script id="plantTemplate" type="text/x-handlebars-template">
    <div class="modal-content">
        <div class="modal-header plant-modal-header">
            <h4 class="modal-title">{{plantInformation.plantName}}</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-9">

                    <table class="table table-striped">
                        <tr>
                            <td>In je tuin sinds</td>
                            <td>{{startDate}}</td>
                        </tr>
                        <tr>
                            <td>Indicatie voor de groeitijd:</td>
                            <td>{{plantInformation.growTime}} dagen</td>
                        </tr>
                        <tr>
                            <td>Oogsttijd:</td>
                            <td>{{plantInformation.harvestingStart}} t/m {{plantInformation.harvestingEnd}}</td>
                        </tr>
                    </table>
                </div>
                <div class="col-sm-3 plant-modal-image">
                    <img src="/plant/{{plantId}}/image">
                </div>
            </div>
        </div>

        <div class="container">
            <div class="alert alert-success" id="harvestDiv">
                Wil je deze plant oogsten en verwijderen uit je tuin?
                <a class="btn btn-success harvestButton" href="/plant/delete/{{plantId}}">Nu oogsten</a>
            </div>
        </div>

        <div class="modal-footer">
            <button type="button" class="btn btn-success" id="harvestBtn">Oogsten</button>
            <button type="button" class="btn btn-success" data-dismiss="modal">Sluit</button>
        </div>

    </div>
</script>