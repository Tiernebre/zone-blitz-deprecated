import { defineConfig } from 'unocss'
import presetUno from '@unocss/preset-uno'

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
    presetUno()
  ],
})
