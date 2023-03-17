import { useState } from "react";
import { Button } from "../../atoms/Button/Button";
import { Input } from "../../atoms/Input/Input";
import { Text } from "../../atoms/Text/Text";
import { StyledCreateProjectPopup } from "./CreateProjectPopup.styles"
import { ReactComponent as Folder } from "../../../assets/folder.svg";
import { UploadFile } from "../../atoms/UploadFile/UploadFile";

interface CreateProjectPopupProps {
    closePopup: any;
}

export const CreateProjectPopup = ({closePopup} : React.PropsWithChildren<CreateProjectPopupProps>) => {

    return (
        <StyledCreateProjectPopup>
            <Text size="large" weight="semi" mb="3.2rem">Criar novo projeto</Text>
            <Input type="text" icon={<Folder />} placeholder="Nome do projeto" value="" mb="1.6rem"></Input>
            <UploadFile mb="3.2rem"/>
            <Button type="button" variant="primary" mb="1.6rem" disabled>Criar projeto</Button>
            <Button type="button" variant="secondary" onClick={() => {closePopup(false)}}>Cancelar</Button>
        </StyledCreateProjectPopup>
    )
}