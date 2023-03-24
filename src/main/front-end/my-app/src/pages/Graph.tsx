import FooterGraph from "../components/FooterGraph";
import SidebarGraph from "../components/SideBarMap";
import VertexInfo from "../components/vertexInfo";
import VisualizationGraph from "../components/VisualizationGraph";


function Graph() {
    return (
        <div>
            <SidebarGraph />
            <VisualizationGraph />
            <FooterGraph />
            <VertexInfo />
        </div>
    )
}

export default Graph;