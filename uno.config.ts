import { defineConfig } from 'unocss'
import presetUno from '@unocss/preset-uno'
import { presetForms } from '@julr/unocss-preset-forms'

export default defineConfig({
  content: {
    filesystem: [
      'src/main/resources/templates/**/*.mustache'
    ],
    pipeline: {
      include: ['src/main/resources/templates/**/*.mustache']
    }
  },
  presets: [
    presetUno(),
    presetForms(),
  ],
})
