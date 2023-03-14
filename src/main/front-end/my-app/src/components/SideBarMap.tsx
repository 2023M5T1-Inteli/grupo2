import { Button, Box, FormControl } from "@mui/material";
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import Link from '@mui/material/Link';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import InputAdornment from '@mui/material/InputAdornment';
import LogoutIcon from '@mui/icons-material/Logout';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';

function SidebarMap() {
  return (
    <div id='sidebar' className='sidebar'
      style={{
        position: 'fixed',
        top: '0',
        left: '0',
        bottom: '0',
        width: '23%',
        backgroundColor: '#fafafa',
        padding: '30px',
        paddingLeft: '50px',
        float: 'left',
      }}>
      <Link href="/projects" id='returnButton' fontWeight="bold" underline="hover" color="inherit">Voltar</Link>
      <br /><br /> Você está em
      <Typography variant="h4" fontWeight="bold">Operação 1</Typography>
      <br />
      <FormControl>
        <Box>Origem A:</Box>
        <TextField id='latitude' name='latitude' className='coordinatesInput' placeholder='Latitude de Origem'
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
                <LogoutIcon />
              </InputAdornment>
            ),
          }}
          sx={{
            width: '272px',
            marginTop: '5%',
            height: '25px',
          }} />
        <TextField id='longitude' name='longitude' className='coordinatesInput' placeholder='Longitude de Origem'
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
                <LogoutIcon />
              </InputAdornment>
            ),
          }}
          sx={{
            width: '272px',
            marginTop: '15%',
            height: '35px',
          }} />
        <br /><br />
        <Box>Destino B:</Box>
        <TextField id='latitude2' name='latitude2' className='coordinatesInput' placeholder='Latitude de Destino'
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
              <ExitToAppIcon />
            </InputAdornment>
            ),
          }}
          sx={{
            width: '272px',
            marginTop: '5%',
            height: '25px',
          }} /> < br />
        <TextField id='longitude2' name='longitude2' className='coordinatesInput' placeholder='Longitude de Destino'
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
              <ExitToAppIcon />
            </InputAdornment>
            ),
          }}
          sx={{
            width: '272px',
            marginTop: '8%',
            height: '60px',
          }} />
      </FormControl>
      <br /><br />
      <Button id='getPathButton' variant="contained" size="large"
        sx={{
          backgroundColor: '#000000',
          padding: '17px',
          borderRadius: '10px'
        }} >Encontrar Melhor Trajeto </Button>
    </div>
  );
}

export default SidebarMap;