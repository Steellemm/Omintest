import { FieldError } from 'react-hook-form'


export interface InputPropsType {
    title: string
    placeholder?: string
    error?: FieldError
    active?: boolean
    checked?: boolean
    value?: string
    onChange: (params: string) => void
}
