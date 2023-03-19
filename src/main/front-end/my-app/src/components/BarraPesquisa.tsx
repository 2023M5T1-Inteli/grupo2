import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';

function BarraPesquisa() {
  return (
    <Box
      component="form"
      sx={{
        position: 'absolute',
        top: '14%',
        left: '67%',
        transform: 'translate(-50%, -50%)',
        '& > :not(style)': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField id="outlined-basic" label="Pesquisar Projeto" variant="outlined" />
    </Box>
  );
}

export default BarraPesquisa;