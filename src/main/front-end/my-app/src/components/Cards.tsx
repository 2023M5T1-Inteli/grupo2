import React, { useState } from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";

import { Link } from 'react-router-dom';

function Card1() {
  const [open, setOpen] = useState(false);

  const handleMenuClick = () => {
    setOpen(true);
  };

  const handleDialogClose = () => {
    setOpen(false);
  };

  return (
    <Link to="/map">
      <Card
        sx={{ maxWidth: 345 }}
        style={{
          position: "absolute",
          left: "15%",
          top: "50%",
          transform: "translate(-50%, -50%)",
        }}
      >
        <CardMedia
          sx={{ height: 100 }}
          image="https://veja.abril.com.br/wp-content/uploads/2022/05/ABRE-2-TOP-GUN-MAVERICK-45.jpg.jpg?quality=70&strip=info&w=1280&h=720&crop=1"
          title="Top Gun Maverick"
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            Operação Brother Sam
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Data: 20/01/2021
          </Typography>
        </CardContent>
        <CardActions sx={{ display: "flex", justifyContent: "flex-end" }}>
          <IconButton sx={{ ml: "auto" }} onClick={handleMenuClick}>
            <MenuIcon />
          </IconButton>
        </CardActions>
        <Dialog open={open} onClose={handleDialogClose}>
          <DialogTitle>Opções</DialogTitle>
          <DialogContent>
            <Typography variant="body1">Editar</Typography>
            <Typography variant="body1">Deletar</Typography>
          </DialogContent>
          <DialogActions>
            <Button onClick={handleDialogClose}>Fechar</Button>
          </DialogActions>
        </Dialog>
      </Card>
    </Link>
  );
}

function Card2() {
  const [open, setOpen] = useState(false);

  const handleMenuClick = () => {
    setOpen(true);
  };

  const handleDialogClose = () => {
    setOpen(false);
  };

  return (
    <Card
      sx={{ maxWidth: 345 }}
      style={{
        position: "absolute",
        left: "40%",
        top: "50%",
        transform: "translate(-50%, -50%)",
      }}
    >
      <CardMedia
        sx={{ height: 100 }}
        image="https://lovingnewyork.com.br/wp-content/uploads/2018/09/loivng-new-york-como-chegar-em-nova-york-e1537864035687.jpg"
        title="Top Gun Maverick"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          Operação Brother Sam
        </Typography>
        <Typography variant="body2" color="text.secondary">
          Data: 22/12
          /2020
        </Typography>
      </CardContent>
      <CardActions sx={{ display: "flex", justifyContent: "flex-end" }}>
        <IconButton sx={{ ml: "auto" }} onClick={handleMenuClick}>
          <MenuIcon />
        </IconButton>
      </CardActions>
      <Dialog open={open} onClose={handleDialogClose}>
        <DialogTitle>Opções</DialogTitle>
        <DialogContent>
          <Typography variant="body1">Editar</Typography>
          <Typography variant="body1">Deletar</Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDialogClose}>Fechar</Button>
        </DialogActions>
      </Dialog>
    </Card>
  );
}

function Cards() {
  return (
    <div>
      <Card1 />
      <Card2 />
    </div>
  );
}

export default Cards;

