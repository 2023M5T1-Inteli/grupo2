import { useState } from "react";
import { StyledInput, StyledInputProps } from "./Input.styles"

interface InputProps extends StyledInputProps {
    id?: string;
    icon: any;
    type: "button" | "checkbox" | "color" | "date" | "datetime-local" | "email" | "file" | "hidden" | "image" | "month" | "number" | "password" | "radio" | "range" | "reset" | "search" | "submit" | "tel" | "text" | "time" | "url" | "week";
    placeholder: string;
    value: string;
    disabled ?: boolean;
    onChange ?: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

export const Input = ({placeholder, icon, mb, mt, ml, mr, type, value, disabled, id, onChange} : React.PropsWithChildren<InputProps>) => {

    const [inputValue, setInputValue] = useState<string>(value);

    const handleInputValue = (event: React.ChangeEvent<HTMLInputElement>) => {
        setInputValue(event.target.value);
        onChange && onChange(event);
    }

    return (
        <StyledInput mb={mb} mt={mt} ml={ml} mr={mr}>
            {icon && icon}
            <input type={type} placeholder={placeholder} value={inputValue} onChange={handleInputValue} disabled={disabled} id={id}/>
        </StyledInput>
    )
}