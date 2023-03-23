import { Tag } from "../../components/atoms/Tag/Tag"
import { StyledControls, StyledCoordinateLabel, StyledFindingBestRoute, StyledForm, StyledMapZone, StyledPageDescription, StyledProject, StyledProjectContent, StyledProjectSidebar } from "./Project.style"
import { ReactComponent as Back } from "../../assets/back.svg";
import { Link, useParams } from "react-router-dom";
import { Text } from "../../components/atoms/Text/Text";
import { ReactComponent as A } from "../../assets/a.svg";
import { ReactComponent as B } from "../../assets/b.svg";
import { Input } from "../../components/atoms/Input/Input";
import { ReactComponent as Exit } from "../../assets/exit.svg";
import { Button } from "../../components/atoms/Button/Button";
import { Slider } from "../../components/atoms/Slider/Slider";
import { useEffect, useRef, useState } from "react";
import axios from "axios";
import mapboxgl from "mapbox-gl";
import { ReactComponent as Loading } from "../../assets/loading.svg";
import { Notyf } from "notyf";
import { MapStyleToggle } from "../../components/molecules/MapStyleToggle/MapStyleToggle";

mapboxgl.accessToken = "pk.eyJ1IjoiZWxpYXNiaW9uZG8iLCJhIjoiY2xmaWFlNHo0MDA5bDNwbWk4bjJlYjh4YiJ9.q4l1ugFTPCZi70qHmWkM7g";

interface IProject {
    id: string;
    name: string;
    dt2file: string;
    createdAt: string;
    status: "processing" | "processed" | "routing" | "routed";
    map?: {
        topLeft: [number, number],
        topRight: [number, number],
        bottomLeft: [number, number],
        bottomRight: [number, number]
    },
    vertices?: [{
        id: string,
        latitude: number,
        longitude: number
    }],
}

const FindingBestRoute = () => {

    const [countdown, setCountdown] = useState(3);

    useEffect(() => {

        const interval = setInterval(() => {
            setCountdown(countdown - 1);
        }, 1000);

        if (countdown === 0) {
            clearInterval(interval);
            window.location.reload();
        }

        return () => clearInterval(interval);

    }, [countdown]);

    return (
        <StyledFindingBestRoute>
            <Loading />
            <Text size="large" weight="semi" color="#18181B" mt="1.6rem">Aguarde enquanto encontramos a melhor rota...</Text>
            <Text mt="1.6rem">Recarregando a página em {countdown} segundos...</Text>
        </StyledFindingBestRoute>
    )
}

const CoordinateLabel = ({ text, icon }: { text: string, icon: any }) => {
    return (
        <StyledCoordinateLabel>
            <Text size="small" weight="medium" mr="0.6rem" color="#667085">{text}</Text>
            {icon && icon}
        </StyledCoordinateLabel>
    )
}

const PageDescription = ({ projectName }: { projectName: string }) => {

    return (
        <StyledPageDescription>
            <Text size="small" weight="medium" color="#667085">Você está em</Text>
            <Text size="large" weight="semi" color="#18181B">{projectName}</Text>
        </StyledPageDescription>
    )
}

const ProjectSidebar = ({ project, setProject }: { project: IProject, setProject : any }) => {

    const [disabled, setDisabled] = useState(true);
    const [originLatitude, setOriginLatitude] = useState("");
    const [originLongitude, setOriginLongitude] = useState("");
    const [destinationLatitude, setDestinationLatitude] = useState("");
    const [destinationLongitude, setDestinationLongitude] = useState("");
    const [buttonText, setButtonText] = useState("Encontrar melhor rota");

    useEffect(() => {

        if (originLatitude.length > 0 && originLongitude.length > 0 && destinationLatitude.length > 0 && destinationLongitude.length > 0 && project.status != "routed") {
            setDisabled(false);
        } else {
            setDisabled(true);
        }

        if (project.status === "routed") {
            setButtonText("Melhor rota encontrada");
            setOriginLatitude(String(project.vertices?.[0].latitude.toFixed(5)));
            setOriginLongitude(String(project.vertices?.[0].longitude.toFixed(5)));
            setDestinationLatitude(String(project.vertices?.[project.vertices?.length - 1].latitude.toFixed(5)));
            setDestinationLongitude(String(project.vertices?.[project.vertices?.length - 1].longitude.toFixed(5)));
        }


    }, [originLatitude, originLongitude, destinationLatitude, destinationLongitude, project]);

    const minLatitude = project.map?.bottomRight[0].toFixed(5);
    const maxLatitude = project.map?.topLeft[0].toFixed(5);
    const minLongitude = project.map?.topLeft[1].toFixed(5);
    const maxLongitude = project.map?.bottomRight[1].toFixed(5);

    const handleClick = async () => {

        const json = {
            route: {
                origin: {
                    latitude: parseFloat(originLatitude),
                    longitude: parseFloat(originLongitude)
                },
                destination: {
                    latitude: parseFloat(destinationLatitude),
                    longitude: parseFloat(destinationLongitude)
                }
            }
        }

        const notyf = new Notyf({duration: 3000});

        try {
            
            const response = await axios.post(`http://localhost:3000/api/projects/${project.id}/best-route`, json, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            notyf.success("Solicitação de melhor rota enviada com sucesso!");

            setProject({
                ...project,
                status: "routing"
            })

        } catch (error) {

            notyf.error("Ocorreu um erro ao tentar encontrar a melhor rota. Tente novamente mais tarde.");

        }



    }

    return (
        <StyledProjectSidebar>
            <Link to="/projects">
                <Tag icon={<Back />} iconPosition="left" mb="3.6rem">Voltar</Tag>
            </Link>
            <PageDescription projectName={project.name && project.name || "Carregando..."} />
            <StyledForm status={project.status}>
                <CoordinateLabel text="Origem" icon={<A />} />
                <Input type="number" placeholder="Latitude de origem" icon={<Exit />} value={originLatitude} setValue={setOriginLatitude} mb="1.2rem" onChange={(event) => { setOriginLatitude(event.target.value) }} min={minLatitude} max={maxLatitude} step={0.00001}></Input>
                <Input type="number" placeholder="Longitude de origem" icon={<Exit />} value={originLongitude} setValue={setOriginLatitude} mb="2.4rem" onChange={(event) => { setOriginLongitude(event.target.value) }} min={minLongitude} max={maxLongitude} step={0.00001}></Input>
                <CoordinateLabel text="Destino" icon={<B />} />
                <Input type="number" placeholder="Latitude de destino" icon={<Exit />} value={destinationLatitude} setValue={setDestinationLatitude} mb="1.2rem" onChange={(event) => { setDestinationLatitude(event.target.value) }} min={minLatitude} max={maxLatitude} step={0.00001}></Input>
                <Input type="number" placeholder="Longitude de destino" icon={<Exit />} value={destinationLongitude} setValue={setDestinationLongitude} mb="3.6rem" onChange={(event) => { setDestinationLongitude(event.target.value) }} min={minLongitude} max={maxLongitude} step={0.00001}></Input>
                <Button variant="primary" type="button" disabled={disabled} onClick={async () => {await handleClick()}}>{buttonText}</Button>
            </StyledForm>
        </StyledProjectSidebar>
    )
}

const MapZone = ({ project }: { project: IProject }) => {

    if (!project.map) {
        return <></>
    }

    const topLeftLongitude = project.map.topLeft[1];
    const topLeftLatitude = project.map.topLeft[0];
    const bottomRightLongitude = project.map.bottomRight[1];
    const bottomRightLatitude = project.map.bottomRight[0];
    const centeredLongitude = (topLeftLongitude + bottomRightLongitude) / 2;
    const centeredLatitude = (topLeftLatitude + bottomRightLatitude) / 2;

    const mapContainer = useRef("");
    const map = useRef<any>(null);
    const [lng, setLng] = useState(centeredLongitude);
    const [lat, setLat] = useState(centeredLatitude);
    const [zoom, setZoom] = useState(9);

    const [circleA, setCircleA] = useState(project.vertices ? [project.vertices[0].longitude, project.vertices[0].latitude] : [0, 0]);
    const [circleB, setCircleB] = useState(project.vertices ? [project.vertices[project.vertices.length - 1].longitude, project.vertices[project.vertices.length - 1].latitude] : [0, 0]);
    const [pathCoordinates, setPathCoordinates] = useState(project.vertices?.map((vertex) => [vertex.longitude, vertex.latitude]) || []);

    const bounds = [
        topLeftLongitude, topLeftLatitude, bottomRightLongitude, bottomRightLatitude
    ];

    const [mapStyle, setMapStyle] = useState('mapbox://styles/mapbox/outdoors-v11');

    useEffect(() => {
        if (map.current) return;

        map.current = new mapboxgl.Map({
            container: mapContainer.current,
            // Satelite style
            style: mapStyle,
            center: [centeredLongitude, centeredLatitude],
            zoom: zoom,
        });

        map.current.addControl(new mapboxgl.NavigationControl(), 'top-left');

        map.current.on('load', () => {

            map.current.addControl(new mapboxgl.ScaleControl({ maxWidth: 150, unit: 'metric' }), 'bottom-left');

            addLayers(map);
            
    
        });
    });

    const addLayers = (map : any) => {
        map.current.addSource('rectangle-hole', {
            type: 'geojson',
            data: {
                type: 'Feature',
                geometry: {
                    type: 'Polygon',
                    coordinates: [
                        [
                            [-180, -90],
                            [180, -90],
                            [180, 90],
                            [-180, 90],
                            [-180, -90],
                        ],
                        [
                            [topLeftLongitude, topLeftLatitude],
                            [bottomRightLongitude, topLeftLatitude],
                            [bottomRightLongitude, bottomRightLatitude],
                            [topLeftLongitude, bottomRightLatitude],
                            [topLeftLongitude, topLeftLatitude],
                        ],
                    ],
                },
            },
        });

        // Add the rectangle hole layer to the map
        map.current.addLayer({
            id: 'rectangle-hole',
            type: 'fill',
            source: 'rectangle-hole',
            layout: {},
            paint: {
                'fill-color': '#E0E0E0',
                'fill-opacity': 1,
            },
        });

        addPath();
        addCircles();
        addCircleALabel();
        addCircleBLabel();

        map.current.on('click', 'circleA', (e:any) => {
            const coordinates = e.features[0].geometry.coordinates.slice();
            showPopup(coordinates, `<b>Origem</b>: <br>Latitude: ${coordinates[1].toFixed(5)}<br> Longitude: ${coordinates[0].toFixed(5)}`);
        });
        
        map.current.on('click', 'circleB', (e:any) => {
            const coordinates = e.features[0].geometry.coordinates.slice();
            showPopup(coordinates, `<b>Destino</b>: <br>Latitude: ${coordinates[1].toFixed(5)}<br> Longitude: ${coordinates[0].toFixed(5)}`);
        });
        
        map.current.on('click', 'path', (e:any) => {
            const coordinates = e.lngLat;
            const distance = calculateDistance(circleA, circleB);
            showPopup(coordinates, `Distância: ${distance} km`);
        });
        
        // Adicione o cursor "pointer" quando passar o mouse sobre os pontos A e B e o caminho
        map.current.on('mouseenter', 'circleA', () => {
            map.current.getCanvas().style.cursor = 'pointer';
        });
        map.current.on('mouseleave', 'circleA', () => {
            map.current.getCanvas().style.cursor = '';
        });
        
        map.current.on('mouseenter', 'circleB', () => {
            map.current.getCanvas().style.cursor = 'pointer';
        });
        map.current.on('mouseleave', 'circleB', () => {
            map.current.getCanvas().style.cursor = '';
        });
        
        map.current.on('mouseenter', 'path', () => {
            map.current.getCanvas().style.cursor = 'pointer';
        });
        map.current.on('mouseleave', 'path', () => {
            map.current.getCanvas().style.cursor = '';
        });

        
    }

    function removeLayers() {
        map.current.removeLayer("rectangle-hole");
        map.current.removeLayer("path");
        map.current.removeLayer("circleA");
        map.current.removeLayer("circleB");
        map.current.removeLayer("circleALabel");
        map.current.removeLayer("circleBLabel");
    
        map.current.removeSource("rectangle-hole");
        map.current.removeSource("path");
        map.current.removeSource("circleA");
        map.current.removeSource("circleB");
        map.current.removeSource("circleALabel");
        map.current.removeSource("circleBLabel");
    }


    const toggleMapStyle = (style : any) => {
        removeLayers();
        setMapStyle(style);
        map.current.setStyle(style);
        map.current.on('style.load', () => {
            addLayers(map);
        })
    };

    function showPopup(coordinates : any, text : any) {
        const popup = new mapboxgl.Popup({ closeButton: false, closeOnClick: true, offset: [0, -10] })
            .setLngLat(coordinates)
            .setHTML(`<p>${text}</p>`)
            .addTo(map.current);
    }
    
    function calculateDistance(coord1 : any, coord2 : any) {
        const R = 6371; // Raio da Terra em km
        const dLat = (coord2[1] - coord1[1]) * (Math.PI / 180);
        const dLon = (coord2[0] - coord1[0]) * (Math.PI / 180);
        const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(coord1[1] * (Math.PI / 180)) * Math.cos(coord2[1] * (Math.PI / 180)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        const d = R * c;
        return d.toFixed(2);
    }
    

    function addCircles() {
        map.current.addSource("circleA", {
            type: "geojson",
            data: {
                type: "Feature",
                geometry: {
                    type: "Point",
                    coordinates: circleA,
                },
            },
        });
    
        map.current.addSource("circleB", {
            type: "geojson",
            data: {
                type: "Feature",
                geometry: {
                    type: "Point",
                    coordinates: circleB,
                },
            },
        });
    
        map.current.addLayer({
            id: "circleA",
            type: "circle",
            source: "circleA",
            paint: {
                "circle-radius": [
                    "interpolate",
                    ["linear"],
                    ["zoom"],
                    5, 5,
                    16, 24,
                ],
                "circle-color": "#2F80ED",
            },
        });
    
        map.current.addLayer({
            id: "circleB",
            type: "circle",
            source: "circleB",
            paint: {
                "circle-radius": [
                    "interpolate",
                    ["linear"],
                    ["zoom"],
                    5, 5,
                    16, 24,
                ],
                "circle-color": "#2F80ED",
            },
        });
    }
    
    function addPath() {
        map.current.addSource("path", {
            type: "geojson",
            data: {
                type: "Feature",
                geometry: {
                    type: "LineString",
                    coordinates: pathCoordinates,
                },
            },
        });
    
        map.current.addLayer({
            id: "path",
            type: "line",
            source: "path",
            layout: {
                "line-join": "round",
                "line-cap": "round",
            },
            paint: {
                "line-color": "#2F80ED",
                "line-width": [
                    "interpolate",
                    ["linear"],
                    ["zoom"],
                    5, 1,
                    16, 5,
                ],
            },
        });
    }
    
    function addCircleALabel() {
        map.current.addSource("circleALabel", {
            type: "geojson",
            data: {
                type: "Feature",
                geometry: {
                    type: "Point",
                    coordinates: circleA,
                },
                properties: {
                    title: "A",
                },
            },
        });
    
        map.current.addLayer({
            id: "circleALabel",
            type: "symbol",
            source: "circleALabel",
            layout: {
                "text-field": "{title}",
                "text-font": ["Open Sans Bold"],
                "text-size": [
                    "interpolate",
                    ["linear"],
                    ["zoom"],
                    5, 3,
                    16, 12,
                ],
                "text-offset": [0, 0], // Posição relativa ao círculo
            },
            paint: {
                "text-color": "#FFFFFF",
            },
        });
    }
    
    function addCircleBLabel() {
        map.current.addSource("circleBLabel", {
            type: "geojson",
            data: {
                type: "Feature",
                geometry: {
                    type: "Point",
                    coordinates: circleB,
                },
                properties: {
                    title: "B",
                },
            },
        });
    
        map.current.addLayer({
            id: "circleBLabel",
            type: "symbol",
            source: "circleBLabel",
            layout: {
                "text-field": "{title}",
                "text-font": ["Open Sans Bold"],
                "text-size": [
                    "interpolate",
                    ["linear"],
                    ["zoom"],
                    5, 3,
                    16, 12,
                ],
                "text-offset": [0, 0], // Posição relativa ao círculo
            },
            paint: {
                "text-color": "#FFFFFF",
            },
        });
    }
    

    return (
        <>
            <StyledMapZone ref={mapContainer} className="map-container" />
            <MapStyleToggle mapStyle={mapStyle} setMapStyle={toggleMapStyle} />
        </>

    )
}

const Controls = () => {
    return (
        <StyledControls>
            <Slider firstOption="Mapa" secondOption="Grafo"></Slider>
        </StyledControls>
    )
}

const ProjectContent = ({ project }: { project: IProject }) => {
    return (
        <StyledProjectContent id="project-content">
            <MapZone project={project} />
            <Controls />
        </StyledProjectContent>
    )
}

export const Project = () => {

    const { projectId } = useParams();

    const [project, setProject] = useState<IProject>({ id: "", name: "", dt2file: "", createdAt: "", status: "processing" });

    const loadProject = async () => {

        try {
            const response = await axios.get(`http://localhost:3000/api/projects/${projectId}`, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            setProject(response.data?.success.data);
        } catch {
            setProject({ id: "", name: "", dt2file: "", createdAt: "", status: "processing" });
        }
    }

    useEffect(() => {
        loadProject();
    }, []);


    return (
        <StyledProject>
            { project.status === "routing" ? <FindingBestRoute /> : (
                <>
                    <ProjectSidebar project={project} setProject={setProject}/>
                    <ProjectContent project={project} />
                </>
            )}
        </StyledProject>
    )
}