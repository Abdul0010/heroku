$(document).ready( function () {
    var table = $('#datatable').DataTable({
        "sAjaxSource": "/scanData",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "bssid" },
            { "mData": "essid" },
            { "mData": "localTime"},
            { "mData": "gpstime" },
            { "mData": "power" },
            { "mData": "security" },
            { "mData": "latitude" },
            { "mData": "longitude" },
            { "mData": "latitudeError" },
            { "mData": "longitudeError" },
            { "mData": "type" },
        ]
    })
});