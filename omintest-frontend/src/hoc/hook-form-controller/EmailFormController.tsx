import { FC } from 'react'
import { Controller } from 'react-hook-form'
import InputField from '../../components/inputs/input-field/InputField.tsx'
import { IAuthFormControlerPropsTypes } from '../../types/pages/auth-form-controler-props-types.ts'


const EmailFormController: FC<IAuthFormControlerPropsTypes> = ({ control, name }) => {
    return (
        <Controller render={({
            field: { onChange }, fieldState: {
                error
            }
        }) => {
            return (
                <InputField
                    placeholder="omintest@login.ru"
                    title={'Электронная почта'}
                    onChange={onChange}
                    error={error}
                />
            )
        }}
        name={name}
        control={control}
        rules={{
            required: {
                value: true,
                message: 'Email не может быть пустым'
            },
            pattern: {
                value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                message: 'Неверный формат email'
            }
        }}
        />
    )
}

export default EmailFormController
