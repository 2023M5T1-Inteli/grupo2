  function GetNodeInfo(nodeId: number, altitude: number, latitude: number, longitude: number) {
      document.getElementById("vertexInfo")!.style.display = "block";
      document.getElementById("vertexId")!.innerHTML = "<b> Vértice " + (nodeId + 1) + "<b>";
      document.getElementById("vertexAltitude")!.innerHTML = "Altitude: " + altitude;
      document.getElementById("vertexLatitude")!.innerHTML = "Latitude: " + latitude;
      document.getElementById("vertexLongitude")!.innerHTML = "Longitude: " + longitude;
      document.getElementById("vertexInfo")!.style.left = document.getElementById("node" + nodeId)! + "px";
      document.getElementById("vertexInfo")!.style.top = document.getElementById("node" + nodeId)! + "px";
      console.log("Dasdwdas")
      return "GetNodeInfo(" + nodeId + "," + altitude + "," + latitude + "," + longitude + ")";
    }

export default GetNodeInfo;