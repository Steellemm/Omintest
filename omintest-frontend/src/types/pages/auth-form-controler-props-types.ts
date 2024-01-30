import { Control } from 'react-hook-form'
import { IFormData } from './auth-types.ts'

export interface IAuthFormControlerPropsTypes {
    control: Control<IFormData>
    name: keyof IFormData
}
