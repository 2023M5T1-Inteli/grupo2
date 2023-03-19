import FooterGraph from "../components/FooterGraph";
import SidebarMap from "../components/SideBarMap";
import VisualizationGraph from "../components/VisualizationGraph";


function Graph() {
    return(
        <div>
            <SidebarMap />
            <VisualizationGraph/>       
            <FooterGraph/>
        </div>
    )
}

export default Graph;