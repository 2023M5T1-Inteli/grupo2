import * as d3 from "d3";
import GetNodeInfo from "./GetNodeInfo";

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
  [832, -22.97, -45.96],
  [845, -22.97, -45.97],
  [558, -22.97, -45.98],
  [422, -22.97, -45.99],
  [934, -22.97, -46.00],
  [1100, -22.97, -46.01],
];

const nodes: Node[] = [];

const links: Link[] = [];

let graphGenerated = 0;

const createPath = (): void => {
  if (graphGenerated == 0){
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
    graphGenerated ++;
  }
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
    .style("stroke", function(d) { return "#000000"; })
    .style("stroke-opacity", function(d) { return "1"; });

  const node = svg.selectAll(".node")
    .data(nodes)
    .enter()
    .append("g")
    .attr("class", "node")
    .attr("id",function(d: any) {return "node" + (d as any).index})
    // .attr("onmouseover", function(d: any) {return "GetNodeInfo(" + d.index + "," + d.altitude + "," + d.latitude + "," + d.longitude + ")"});
    // .attr("onmouseout", function(d: any) {return hideNodeInfo});
    
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

export default createPath;