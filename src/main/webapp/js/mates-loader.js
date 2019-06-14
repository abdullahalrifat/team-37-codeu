// Initialize and add the map
var currentLat = -25.344;
var currentLong = 131.036;
var map;
function initMap() {
    // The location of Uluru
    var uluru = {lat: currentLat, lng: currentLong};
    // The map, centered at Uluru
    map = new google.maps.Map(
        document.getElementById('map'), {zoom: 10, center: uluru});

    const url = '/travelMates';
    const groupBy = key => array =>
        array.reduce((objectsByKeyValue, obj) => {
            const value = obj[key];
            objectsByKeyValue[value] = (objectsByKeyValue[value] || []).concat(obj);
            return objectsByKeyValue;
        }, {});

    fetch(url)
        .then((response) => {
            return response.json();
        })
        .then((mates) => {
            const groupByUser = groupBy('user');
            const groupUser = groupByUser(mates);
            const list = JSON.stringify({
                groupUser: groupByUser(mates)
            }, null, 1);
            for (var commonuser in groupUser){
                //alert(commonuser);
                var pos = {lat: groupUser[commonuser][0].alat, lng:groupUser[commonuser][0].along};

                //alert(pos);
                // The marker, positioned at Uluru
                var marker = new google.maps.Marker({position: pos, map: map,label:groupUser[commonuser][0].user});
                /*for(var i = 0; i < groupUser[commonuser].length; i++){
                    var pos = {lat: groupUser[commonuser][i].alat, lng:groupUser[commonuser][i].along};

                    //alert(pos);
                    // The marker, positioned at Uluru
                    var marker = new google.maps.Marker({position: pos, map: map});
                }*/
            }

            //alert(list);
        });





}
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        // x.innerHTML = "Geolocation is not supported by this browser.";
    }
}

function showPosition(position) {
    currentLat = position.coords.latitude;
    currentLong = position.coords.longitude;

    $.ajax({
        url:'travelMates',
        type:'POST',
        data:{
            'alat':currentLat,
            'along':currentLong
        },
        success : function(data){
            //alert('success'+data);
        }
    });

    // x.innerHTML = "Latitude: " + position.coords.latitude +
    //    "<br>Longitude: " + position.coords.longitude;
    initMap();

}

function resizeMap()
{
    google.maps.event.trigger(map,'resize');
}
