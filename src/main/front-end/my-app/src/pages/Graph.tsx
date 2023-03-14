import FooterGraph from "../components/FooterGraph";
import SidebarGraph from "../components/SideBarGraph";
import VisualizationGraph from "../components/VisualizationGraph";


function Graph() {
    return (
        <div>
            <SidebarGraph />
            <VisualizationGraph />
            <FooterGraph />
        </div>
    )
}

export default Graph;