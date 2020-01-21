function testReadSingleFile() {
    console.log("Current path " +  window.location.pathname);
    var path = document.getElementById('filepath').innerText;
    var props = readSingleFile(path);
    document.getElementById('output').innerText = props;
}

function testReadPropertiesFile() {
    console.log("Current path " +  window.location.pathname);
    var path = document.getElementById('filepath').innerText;
    var jsonFormatProps = readSingleFile(path);
    document.getElementById('output').innerText = jsonFormatProps;
}
