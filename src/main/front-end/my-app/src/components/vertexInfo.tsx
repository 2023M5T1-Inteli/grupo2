import { Box } from "@mui/material";

function vertexInfo() {
    return (
        <div id='vertexInfo'
        style={{
            display: 'none',
            position: 'absolute',
            borderRadius: '5',
            border: '1px solid #000',
            padding: '10px',
            zIndex: '1',
        }}>
            <Box id='vertexId'></Box><br />
            <Box id='vertexAltitude'></Box><br />
            <Box id='vertexLatitude'></Box><br />
            <Box id='vertexLongitude'></Box>
        </div>
    );
}

export default vertexInfo;