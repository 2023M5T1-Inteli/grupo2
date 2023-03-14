import Cards from "./components/Cards"
import Texto2 from "./components/Texto2"
import BarraPesquisa from "./components/BarraPesquisa";
import ModalNewProject from "./components/ModalNewProject";


function App() {
  return (
    <>
      <ModalNewProject />
      <Cards />
      <Texto2 />
      <BarraPesquisa />
    </>

  )
}

export default App;


// return(
//   <BrowserRouter>
//   <BotaoPg2 />
//   <Cards />
//   <Texto2 />
//   </BrowserRouter>
// )*/

// mudar
// import React from 'react';
// import './App.css';

// //install: npm install @mui/material @emotion/react @emotion/styled
// import Grid from '@mui/material/Grid'; // Grid version 1
// import Box from '@mui/material/Box';
// import Paper from '@mui/material/Paper';
// import { styled } from '@mui/material/styles';


// const Item = styled(Paper)(({ theme }) => ({
//   backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
//   ...theme.typography.body2,
//   padding: theme.spacing(1),
//   textAlign: 'center',
//   height: 550,
//   color: theme.palette.text.secondary,
// }));

// const Footer = styled(Paper)(({ theme }) => ({
//   backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
//   ...theme.typography.body2,
//   padding: 10,
//   textAlign: 'center',
//   height: 50,
//   width: 800,
//   color: theme.palette.text.secondary,
// }));

// function App() {
//   return (
//     <div>
//       <div id='sidebar' className='sidebar'>
//         <a href='#' id='returnButton'> Voltar</a>
//         <br /><br />Você está em
//         <h2>Operação 1</h2>
//         <br/>
//         <form>
//           <label>Origem A:</label> <br/>
//           <input type='text' id='latitude' name='latitude' className='coordinatesInput' placeholder='Latitude de Origem'/>
//             <input type='text' id='longitude' name='longitude' className='coordinatesInput' placeholder='Longitude de Origem '/>
//               <br /><br />
//               <label>Destino B:</label> <br/>
//               <input type='text' id='latitude2' name='latitude2' className='coordinatesInput' placeholder='Latitude de Destino'/>
//                 <input type='text' id='longitude2' name='longitude2' className='coordinatesInput' placeholder='Longitude de Destino'/>
//           </form>
//                 <br /><br />
//                 <button id='getPathButton'>Encontrar Melhor Trajeto</button>
//         </div>

//               <div id='visualization' className='visualization'>
//                 <div id='vertexInfo'>
//                   <label id='vertexId'></label><br />
//                   <label id='vertexAltitude'></label><br />
//                   <label id='vertexLatitude'></label><br />
//                   <label id='vertexLongitude'></label>
//                 </div>
//               </div>

//               <div className='container-fluid' id='footer-lat-long'>
//                 <div className='bottom-nav'>
//                   <input type='text'/>
//                   <label className='nav-option'>Longitude </label>
//                   <input type='text'/>
//                   <label id='viewLabel'>Latitude</label>
//                 </div>
//               </div>
//     </div>
//   );
// }

// export default App;

// :root {
//   --backgroundColor: #E3E4EA;
//   --whiteTextColor: #FFFF;
// }

// body {
//   background-color: var(--backgroundColor);
// }

// #returnButton {
//   color: #000000;
// }


// .node {
//     background-color: #bbb;
//     border-radius: 50%;
//     display: inline-block;
//   }

// .link {
//     stroke: #000000;
//     stroke-opacity: 1;
// }

// .sidebar {
//   position: fixed;
//   top: 0;
//   left: 0;
//   bottom: 0;
//   width: 23%;
//   background-color: white;
//   padding: 20px;
//   float: left;
// }

// .visualization {
//   position: absolute;
//   top: 0;
//   right: 0;
//   width: 70%;
//   height: 95%;

// }

// .coordinatesInput {
  // width: 300px;
  // border-radius: 5px;
  // margin-top: 5%;
  // height: 45px;
  // padding-left: 20px;

// }

// #getPathButton {
  // width: 90%;
  // height: 10%;
  // border-radius: 5px;
  // background-color: #000000;
  // color: var(--whiteTextColor);
// }

// .bottom-nav {
//   position: fixed;
//   display: flex;
//   bottom: 0;
//   right: 0;
//   flex-direction: row-reverse;
//   float: right;
//   width: 74.15%;
//   background-color: #f5f5f5d3;
//   align-items: center;
//   height: 5%;
// }

// .nav-option {
//   text-decoration: none;
//   color: #333;
//   font-size: 13px;
//   margin: 1.5%;
//   border: 0;
// }

// #viewLabel {
//   margin-left: 2%;
//   padding-right: 2%;
//   padding-left: 200px;
//   margin-top: 0.5%;
// }

// #vertexInfo {
  // display: none;
  // position: absolute;
  // background-color: #fff;
  // border-radius: 5px;
  // border: 1px solid #000;
  // padding: 10px;
  // z-index: 1;
// }

// Futura integração mapa 
// const [map, setMap] = useState(null)
//     function getMap() {
//         fetch("localhost:3000/getMap").then(response => response.json())
//         .then(data => setMap(data));
//     }