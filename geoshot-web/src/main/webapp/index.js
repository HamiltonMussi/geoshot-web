let new_position = { lat: -22.955833, lng: -43.166667 }
document.getElementById('guessForm').addEventListener('submit', function(event) {
    let stored_position = localStorage.getItem("new_position")
    let position_touple = [-22.955833, -43.166667]
    if (stored_position) {
        stored_position = JSON.parse(stored_position);
        position_touple = [stored_position.lat, stored_position.lng];
    }
    console.log(position_touple)
    document.getElementById('newPositionInput').value = String(position_touple);

});

function initMap() {
    const map = new google.maps.Map(document.getElementById("map"), {
        center: new_position,
        zoom: 2,
        mapId: "e576967f8fa06b97",
    });
    const draggableMarker = new google.maps.marker.AdvancedMarkerView({
        map,
        position: new_position,
        gmpDraggable: true,
        title: "Me arraste",
    });
    draggableMarker.addListener("dragend", (event) => {
        const position = draggableMarker.position;
        localStorage.setItem('new_position', JSON.stringify(position));
    });
}
window.initMap = initMap;