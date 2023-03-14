import * as d3 from "d3";

interface Node {
  index: number;
  altitude: number;
  latitude: number;
  longitude: number;
  desiredX?: number;
  desiredY?: number;
  x?: number;
  y?: number;
}

interface Link {
  source: number;
  target: number;
}

const path: [number, number, number][] = [
  [1, -22.97, -45.96],
  [2, -22.97, -45.97],
  [3, -22.97, -45.98],
  [4, -22.97, -45.99],
  [5, -22.97, -46.00],
  [6, -22.97, -46.01],
];

const nodes: Node[] = [];

const links: Link[] = [];

const createPath = (): void => {
  for (let i = 0; i < path.length; i++) {
    const node: Node = {
      index: i,
      altitude: path[i][0],
      latitude: path[i][1],
      longitude: path[i][2],
    };
    nodes.push(node);
    if (i > 0) {
      const link: Link = { source: i - 1, target: i };
      links.push(link);
    }
  }
  generateGraph();
};

function generateGraph(): void {
  const svg = d3.select("div#visualization")
    .append("svg")
    .attr("width", "100%")
    .attr("height", "100%");

  let desiredX = 50;
  let desiredY = 50;
  let sumX = 100;
  nodes.forEach(function(node, i) {
    node.desiredX = desiredX;
    node.desiredY = desiredY;
    desiredX += sumX;
    if (desiredX >= 50 * 17 - 50 || desiredX <= 0 - 50) {
      sumX = -sumX;
      desiredX += sumX;
      desiredY += 50;
    }
  });

  const simulation = d3.forceSimulation(nodes)
    .force("link", d3.forceLink(links).id(function(d: any) { return (d as any).index; }).strength(0))
    .force("x", d3.forceX().x(function(d: any) { return (d as any).desiredX; }).strength(1))
    .force("y", d3.forceY().y(function(d: any) { return (d as any).desiredY; }).strength(1));

  const link = svg.selectAll(".link")
    .data(links)
    .enter()
    .append("line")
    .attr("class", "link");

  const node = svg.selectAll(".node")
    .data(nodes)
    .enter()
    .append("g")
    .attr("class", "node")
    .attr("id",function(d: any) {return "node" + (d as any).index})
    .attr("onmouseover", function(d: any) {return "getNodeInfo(" + (d as any).index + ")"})
    .attr("onmouseout", function(d: any) {return "hideNodeInfo(" + (d as any).index + ")"});
    
  node.append("circle")
    .attr("r", 20)
    .attr("fill", "black");

  node.append("text")
    .attr("text-anchor", "middle")
    .attr("dy", ".35em")
    .attr("fill", "white")
    .style("pointer-events", "none")
    .text(function(d: any) { return (d as any).index + 1; });

  simulation.on("tick", function() {
    link.attr("x1", function(d: { source: any; }) { return (d.source as any).x; })
      .attr("y1", function(d: { source: any; }) { return (d.source as any).y; })
      .attr("x2", function(d: { target: any; }) { return (d.target as any).x; })
      .attr("y2", function(d: { target: any; }) { return (d.target as any).y; });

    node.attr("transform", function(d: any) { return "translate(" + (d as any).x + "," + (d as any).y + ")"; });
  });
}

function getNodeInfo(node: number) {
  document.getElementById("vertexInfo")!.style.display = "block";
  document.getElementById("vertexId")!.innerHTML = "<b> Vértice " + (node + 1) + "<b>";
  document.getElementById("vertexAltitude")!.innerHTML = "Altitude: " + nodes[node].altitude;
  document.getElementById("vertexLatitude")!.innerHTML = "Latitude: " + nodes[node].latitude;
  document.getElementById("vertexLongitude")!.innerHTML = "Longitude: " + nodes[node].longitude;
  //document.getElementById("vertexInfo")!.style.left = d3.select("#node" + node).datum().x + "px";
  //document.getElementById("vertexInfo")!.style.top = d3.select("#node" + node).datum().y + "px";
  hideNodeInfo();
}

function hideNodeInfo() {
  document.getElementById("vertexInfo")!.style.display = "none";
}

export default createPath;