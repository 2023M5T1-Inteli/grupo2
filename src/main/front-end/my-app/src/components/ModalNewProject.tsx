import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import AddIcon from '@mui/icons-material/Add';

const style = {
  position: 'absolute' as 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

const theme2 = createTheme({
  palette: {
    primary: {
      main: '#ffffff'
    }
  },
  components: {
    MuiButton: {
      styleOverrides: {
        root: {
          backgroundColor: 'black',
          color: 'white',
          elevation: 0,
          '&:hover': {
            backgroundColor: 'black',
            color: 'white',
          },
          fontSize: '1.0rem',
          width: '200px',
          padding: 0,
        },
        text: {
          color: 'white',
          fontFamily: 'Plus Jakarta Suns, sans-serif',
        }
      }
    }
  }
});


function ModalNewProject() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <div>
      <ThemeProvider theme={theme2}>
        <Button
          sx={{
            display: 'flex',
            justifyContent: 'center',
            position: 'absolute',
            top: '14%',
            left: '85%',
            transform: 'translate(-50%, -50%)',
            alignItems: 'center',
            '& > :first-child': {
              marginRight: '16px',
            }
          }}
        >
          Criar novo projeto
          <AddIcon color="primary" />
        </Button>
      </ThemeProvider>      
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Text in a modal
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
          </Typography>
        </Box>
      </Modal>
    </div>
  );
}

export default ModalNewProject;