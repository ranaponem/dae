<template>
  <div v-if="errorStudent || errorCourse">Error: {{ error.message }}</div>
  <div v-else>
    <div>
      <nuxt-link to="/createstudent">Create a New Student</nuxt-link>
      <h2>Students</h2>
      <table>
        <thead>
          <tr><th>Username</th><th>Name</th><th>E-mail</th><th>Course</th></tr>
        </thead>
        <tbody>
          <tr v-for="student in students">
            <td>{{ student.username }}</td>
            <td>{{ student.name }}</td>
            <td>{{ student.email }}</td>
            <td>{{ student.courseName }}</td>
          </tr>
        </tbody>
      </table>
      <button @click.prevent="refreshStudent">Refresh students</button>
    </div>
    <div>
      <nuxt-link to="/createcourse">Create a New Course</nuxt-link>
      <h2>Courses</h2>
      <table>
        <thead>
          <tr><th>Code</th><th>Name</th></tr>
        </thead>
        <tbody>
          <tr v-for="course in courses">
            <td>{{ course.code }}</td>
            <td>{{ course.name }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  <button @click.prevent="refresh">Refresh courses</button>
</template>
<script setup>
  const config = useRuntimeConfig()
  const api = config.public.API_URL
  const { data: students, errorStudent, refreshStudent } = await useFetch(`${api}/student`)
  const { data: courses, errorCourse, refreshCourse } = await useFetch(`${api}/course`)
</script>
