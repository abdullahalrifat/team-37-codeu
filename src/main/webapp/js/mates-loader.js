// Initialize and add the map
var currentLat = -25.344;
var currentLong = 131.036;
var map,map_mine;
let editMarker;
function initMap() {
    // The location of Uluru
    var uluru = {lat: currentLat, lng: currentLong};

    // The map, centered at Uluru
    map = new google.maps.Map(
        document.getElementById('map'), {zoom: 10, center: uluru});

    map_mine = new google.maps.Map(
        document.getElementById('map_mine'), {zoom: 10, center: uluru});
    map_mine.addListener('click', (event) => {
        createMarkerForEdit(event.latLng.lat(), event.latLng.lng());
    });
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
                //var pos = {lat: groupUser[commonuser][0].alat, lng:groupUser[commonuser][0].along};

                //alert(pos);
                // The marker, positioned at Uluru
                //var marker = new google.maps.Marker({position: pos, map: map,label:groupUser[commonuser][0].user});
                createMarkerForDisplay( groupUser[commonuser][0].alat, groupUser[commonuser][0].along,groupUser[commonuser][0].user,map);
                //if(commonuser)
                //alert(groupUser[commonuser]);
                /*for(var i = 0; i < groupUser[commonuser].length; i++){
                    var pos = {lat: groupUser[commonuser][i].alat, lng:groupUser[commonuser][i].along};

                    //alert(pos);
                    // The marker, positioned at Uluru
                    var marker = new google.maps.Marker({position: pos, map: map});
                }*/
            }

            //alert(list);
        });
    const travel_timeline_url = '/travelTimeline';
    fetch(travel_timeline_url)
        .then((response) => {
            return response.json();
        })
        .then((mates) => {

            mates.forEach((mate) => {
                //alert(mate.details);
                createMarkerForDisplay( mate.alat, mate.along,mate.details,map_mine);
                //var pos = {lat: mate.alat, lng:mate.along};
                //var marker = new google.maps.Marker({position: pos, map: map_mine});
            });

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

    /*$.ajax({
        url:'travelMates',
        type:'POST',
        data:{
            'alat':currentLat,
            'along':currentLong
        },
        success : function(data){
            //alert('success'+data);
        }
    });*/

    // x.innerHTML = "Latitude: " + position.coords.latitude +
    //    "<br>Longitude: " + position.coords.longitude;
    initMap();

}

function resizeMap()
{
    google.maps.event.trigger(map,'resize');
    google.maps.event.trigger(map_mine,'resize');
}

/** Sends a marker to the backend for saving. */
function postMarker(lat, lng, content){
    const params = new URLSearchParams();
    params.append('alat', lat);
    params.append('along', lng);
    params.append('details', content);
    fetch('/travelTimeline', {
        method: 'POST',
        body: params
    });
}
/** Creates a marker that shows a textbox the user can edit. */
function createMarkerForEdit(lat, lng){
    // If we're already showing an editable marker, then remove it.
    if(editMarker){
        editMarker.setMap(null);
    }
    editMarker = new google.maps.Marker({
        position: {lat: lat, lng: lng},
        map: map_mine
    });
    const infoWindow = new google.maps.InfoWindow({
        content: buildInfoWindowInput(lat, lng)
    });
    // When the user closes the editable info window, remove the marker.
    google.maps.event.addListener(infoWindow, 'closeclick', () => {
        editMarker.setMap(null);
    });
    infoWindow.open(map_mine, editMarker);
}
/** Builds and returns HTML elements that show an editable textbox and a submit button. */
function buildInfoWindowInput(lat, lng){
    const textBox = document.createElement('textarea');
    const button = document.createElement('button');
    button.appendChild(document.createTextNode('Submit'));
    button.onclick = () => {
        postMarker(lat, lng, textBox.value);
        createMarkerForDisplay(lat, lng, textBox.value);
        editMarker.setMap(null);
    };
    const containerDiv = document.createElement('div');
    containerDiv.appendChild(textBox);
    containerDiv.appendChild(document.createElement('br'));
    containerDiv.appendChild(button);
    return containerDiv;
}
/** Creates a marker that shows a read-only info window when clicked. */
function createMarkerForDisplay(lat, lng, content,map_name){
    //alert(content);
    const marker = new google.maps.Marker({
        position: {lat: lat, lng: lng},
        map: map_name
    });
    var infoWindow = new google.maps.InfoWindow({
        content: content
    });
    marker.addListener('click', () => {
        infoWindow.open(map_name, marker);
    });
}